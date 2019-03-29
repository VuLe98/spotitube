package com.spotitube.services.login;

import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dao.token.TokenDAO;
import com.spotitube.entities.Token;
import com.spotitube.entities.User;
import com.spotitube.exceptions.LoginException;

import javax.inject.Inject;

public class LoginServiceImpl implements LoginService{

    @Inject
    private LoginDAO loginDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public Token login(User user) throws LoginException {
        User gebruiker = loginDAO.getUser(user.getUser(),user.getPassword());
        if (gebruiker != null && gebruiker.getPassword().equals(user.getPassword())){
            return tokenDAO.createTokenForUser(gebruiker.getUser());
        } else{
            throw new LoginException("Incorrect login");
        }
    }


}
