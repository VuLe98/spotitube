package com.spotitube.models;

public class UserModel {
    private String user;
    private String token;

    public UserModel(){}

    public UserModel(String user, String token){
        setUser(user);
        setToken(token);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
