package com.blacknebula.gisthub.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hazem
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class GithubUser {
    private String login;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String email;
    private String name;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getAvatarUrl() {
        return avatarUrl;
    }

    void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GithubUser{");
        sb.append("login='").append(login).append('\'');
        sb.append(", avatarUrl='").append(avatarUrl).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
