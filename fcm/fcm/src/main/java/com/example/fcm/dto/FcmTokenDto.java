package com.example.fcm.dto;

public class FcmTokenDto {
    private String token;

    public FcmTokenDto() {}

    public FcmTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
