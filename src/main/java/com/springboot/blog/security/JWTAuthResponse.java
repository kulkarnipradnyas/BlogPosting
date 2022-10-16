package com.springboot.blog.security;

public class JWTAuthResponse {
    private String accessToken;
    private String accessType = "bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
