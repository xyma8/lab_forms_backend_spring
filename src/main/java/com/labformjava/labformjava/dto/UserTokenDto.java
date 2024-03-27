package com.labformjava.labformjava.dto;

public class UserTokenDto {

    private String token;

    public UserTokenDto() {

    }

    public UserTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
