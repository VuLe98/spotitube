package com.spotitube;

import java.time.LocalDateTime;

public class Token {

    private String user;
    private String token;

    public Token(String user, String token){
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
