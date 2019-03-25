package com.spotitube.controller.login;

import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dao.token.TokenDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.dto.LoginResponse;
import com.spotitube.entities.Token;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginController {

    private LoginDAO loginDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginRequest request) {
        UserModel login = loginDAO.login(request.getUser(), request.getPassword());

        if (login.getToken() == null) {
            return Response.status(403).build();
        }

        LoginResponse response = new LoginResponse();

        response.setToken(login.getToken());
        response.setUser(login.getFullname());

        return Response.ok().entity(response).build();
    }


    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}

