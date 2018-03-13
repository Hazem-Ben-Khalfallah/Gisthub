package com.blacknebula.gisthub.security;


public class Principal {

    private final String login;
    private final String token;

    public Principal(String login, String token) {
        this.login = login;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }



}
