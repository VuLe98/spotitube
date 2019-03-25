package com.spotitube.services.login;

import com.spotitube.entities.Token;
import com.spotitube.entities.User;
import com.spotitube.exceptions.LoginException;


public interface LoginService {
    Token login(User user) throws LoginException;
}
