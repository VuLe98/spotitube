package com.spotitube.services.login;

import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dao.token.TokenDAO;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

public class LoginServiceImpl implements LoginService{

    private LoginDAO loginDAO;
    private TokenDAO tokenDAO;

    @Override
    public Response login(String username, String password){
        UserModel user = loginDAO.login(username,password);
        try {
            if (user.getUser() != null) {
                if (user.getToken() != null) {
                    return Response.ok().entity(tokenDAO.updateTokenForUser(username)).build();
                } else {
                    return Response.ok().entity(tokenDAO.createTokenForUser(username)).build();
                }
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Inject
    public void setLoginDAO(LoginDAO dao){
        this.loginDAO = dao;
    }

    @Inject
    public void setTokenDAO(TokenDAO dao){
        this.tokenDAO = dao;
    }

}
