package com.spotitube.controller.login;

import com.spotitube.dto.LoginRequest;
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

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginRequest request) {
        return service.login(request.getUser(),request.getPassword());
    }

    @Inject
    public void setLoginService(LoginService loginService){
        this.service = loginService;
    }

}

