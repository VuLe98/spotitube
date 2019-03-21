package com.spotitube.controller;

import com.spotitube.User;
import com.spotitube.dao.LoginDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.dto.LoginResponse;
import com.spotitube.helpers.LoginHelper;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


    @Path("/login")
    public class LoginController {

        @Inject
        private LoginDAO dao;

//       @Inject
//       private LoginHelper helper;

        @POST
        @Consumes("application/json")
        @Produces("application/json")
        public Response login(LoginRequest request){
            UserModel login = dao.login(request.getUser(), request.getPassword());

            if(login.getToken() == null){
                return Response.status(403).build();
            }
            LoginResponse response = new LoginResponse();
            response.setToken(login.getToken());
            response.setUser(login.getFullname());

            return Response.ok(response).build();
        }
    }

