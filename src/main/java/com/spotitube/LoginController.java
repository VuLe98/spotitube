package com.spotitube;

import dto.LoginRequest;
import dto.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


    @Path("/")
    public class LoginController {

        @POST
        @Path("login")
        @Consumes("application/json")
        @Produces("application/json")
        public Response login(LoginRequest request){
            String token = "51246-kash-e7r";
            String user = "Vu";

            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setUser(user);

            return Response.ok(response).build();
        }

    }

