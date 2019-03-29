package com.spotitube.controller.login;

import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.dto.LoginResponse;
import com.spotitube.entities.Token;
import com.spotitube.entities.User;
import com.spotitube.exceptions.LoginException;
import com.spotitube.services.login.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private LoginService service;
    private LoginDAO loginDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginRequest request) {
        User loginUser = new User(request.getUser(),request.getPassword());

        if(request.getUser() == null || request.getUser().isEmpty()||
                request.getPassword() == null || request.getPassword().isEmpty()){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try {
            return Response.ok().entity(service.login(loginUser)).build();
        }
        catch(LoginException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Inject
    public void setLoginService(LoginService loginService){
        this.service = loginService;
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO){
        this.loginDAO = loginDAO;
    }
}

