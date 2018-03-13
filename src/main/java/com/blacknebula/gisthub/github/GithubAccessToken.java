package com.blacknebula.gisthub.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hazem
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubAccessToken {
    @JsonProperty("access_token")
    private String token;

    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }
}
