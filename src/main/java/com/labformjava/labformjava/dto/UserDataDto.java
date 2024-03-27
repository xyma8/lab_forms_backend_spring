package com.labformjava.labformjava.dto;

public class UserDataDto {
    private String login;
    private String name;

    public UserDataDto(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDataDto() {

    }
}
