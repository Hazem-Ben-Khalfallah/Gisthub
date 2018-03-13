package com.blacknebula.gisthub.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

public class OauthAuthenticationToken extends AbstractAuthenticationToken {

    private Principal principal;
    private Object credentials;

    public OauthAuthenticationToken(String login, String token) {
        super((Collection) null);
        this.principal = new Principal(login, token);
        setAuthenticated(false);
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
