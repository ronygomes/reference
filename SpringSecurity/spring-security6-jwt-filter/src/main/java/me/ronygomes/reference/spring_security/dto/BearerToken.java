package me.ronygomes.reference.spring_security.dto;

import java.io.Serializable;

public class BearerToken implements Serializable {

    private final String accessToken;
    private final String tokenType;

    public BearerToken(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
