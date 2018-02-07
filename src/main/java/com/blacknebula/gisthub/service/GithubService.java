package com.blacknebula.gisthub.service;

import com.blacknebula.gisthub.config.ApplicationProperties;
import com.blacknebula.gisthub.service.util.JsonSerializer;
import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

@Service
public class GithubService {
    private final Logger log = LoggerFactory.getLogger(GithubService.class);
    private final ApplicationProperties applicationProperties;

    public GithubService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String buildOauthUri() {

        // todo save nonce in db

        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
            .fromUriString("https://github.com/login/oauth/authorize")
            .queryParam("client_id", applicationProperties.getGithub().getClient().getId())
            .queryParam("redirect_uri", applicationProperties.getGithub().getCallbackUrl())
            .queryParam("scope", applicationProperties.getGithub().getScope())
            .queryParam("state", generateState());

        return uriComponentsBuilder.toUriString();
    }


    public String oauthCallback(String code, String nonce) {
        // todo check that nonce is the same as the one sent in the oauth dance

        final Map<String, String> input = ImmutableMap.<String, String>builder()
            .put("client_id", applicationProperties.getGithub().getClient().getId())
            .put("client_secret", applicationProperties.getGithub().getClient().getSecret())
            .put("code", code)
            .put("redirect_uri", applicationProperties.getGithub().getRedirectUrl())
            .put("state", nonce)
            .build();

        final ClientResponse response = Client.create()
            .resource("https://github.com/login/oauth/access_token")
            .type(MediaType.APPLICATION_JSON_TYPE)
            .post(ClientResponse.class, JsonSerializer.serialize(input));

        if (!response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            throw new RuntimeException("Failed : HTTP error code [" + response.getStatus() + "] : " + response.getEntity(String.class));
        }

        // todo save token in database
        log.info("Yes!! {}", response.getEntity(String.class));
        return applicationProperties.getGithub().getRedirectUrl();
    }

    private String generateState() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
