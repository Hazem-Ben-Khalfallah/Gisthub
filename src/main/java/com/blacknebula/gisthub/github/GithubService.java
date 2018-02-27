package com.blacknebula.gisthub.github;

import com.blacknebula.gisthub.config.ApplicationProperties;
import com.blacknebula.gisthub.util.DateUtils;
import com.blacknebula.gisthub.util.JsonSerializer;
import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GithubService {
    private final Logger log = LoggerFactory.getLogger(GithubService.class);
    private final ApplicationProperties applicationProperties;
    private final NonceRepository nonceRepository;

    public GithubService(ApplicationProperties applicationProperties, NonceRepository nonceRepository) {
        this.applicationProperties = applicationProperties;
        this.nonceRepository = nonceRepository;
    }

    public String buildOauthUri() {
        final NonceEntity nonce = nonceRepository.save(NonceEntity.newBuilder()
            .id(generateState())
            .creationDate(new Date())
            .build());

        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
            .fromUriString("https://github.com/login/oauth/authorize")
            .queryParam("client_id", applicationProperties.getGithub().getClient().getId())
            .queryParam("redirect_uri", applicationProperties.getGithub().getCallbackUrl())
            .queryParam("scope", applicationProperties.getGithub().getScope())
            .queryParam("state", nonce.getId());

        return uriComponentsBuilder.toUriString();
    }


    public String oauthCallback(String code, String nonce) {
        final Optional<NonceEntity> nonceOptional = nonceRepository.findById(nonce);

        if (!nonceOptional.isPresent()) {
            throw new InvalidNonceException("Invalid nonce value");
        }

        final NonceEntity nonceEntity = nonceOptional.get();
        if (new Date().after(DateUtils.addMinutes(nonceEntity.getCreationDate(), 5))) {
            throw new InvalidNonceException("Deprecated nonce value");
        }

        final Map<String, String> input = ImmutableMap.<String, String>builder()
            .put("client_id", applicationProperties.getGithub().getClient().getId())
            .put("client_secret", applicationProperties.getGithub().getClient().getSecret())
            .put("code", code)
            .put("redirect_uri", applicationProperties.getGithub().getRedirectUrl())
            .put("state", nonceEntity.getId())
            .build();

        final ClientResponse response = Client.create()
            .resource("https://github.com/login/oauth/access_token")
            .type(MediaType.APPLICATION_JSON_TYPE)
            .accept(MediaType.APPLICATION_JSON_TYPE)
            .post(ClientResponse.class, JsonSerializer.serialize(input));

        if (!response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            throw new UnsuccessfulRequestException("Failed : HTTP error code [" + response.getStatus() + "] : " + response.getEntity(String.class));
        }

        final Map map = JsonSerializer.toObject(response.getEntity(String.class), Map.class);
        log.info("here is your token!! {}", map.get("access_token"));
        return applicationProperties.getGithub().getRedirectUrl();
    }

    private String generateState() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
