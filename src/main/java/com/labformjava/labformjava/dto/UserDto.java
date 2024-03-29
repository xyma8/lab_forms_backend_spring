package com.labformjava.labformjava.dto;

public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private int gender;
    private int darktheme;
    private String token;

    public UserDto() {

    }

    public UserDto(Long id, String name, String surname, String email, String login, String password, int gender, int darktheme, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.darktheme = darktheme;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDarktheme() {
        return darktheme;
    }

    public void setDarktheme(int darktheme) {
        this.darktheme = darktheme;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
