package com.chuwa.blog.payload.security;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author b1go
 * @date 6/26/22 5:25 PM
 */
public class SignUpDto {
    private String name;
    @JsonProperty(value = "user_name")
    private String userName;
    private String email;
    private String password;

    public SignUpDto(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
