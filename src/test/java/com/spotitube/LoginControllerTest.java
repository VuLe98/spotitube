package com.spotitube;

import com.spotitube.controller.login.LoginController;
import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.models.UserModel;
import com.spotitube.services.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private static final String USERNAME = "Vu";
    private static final String PASSWORD = "1234";

    private LoginController controller;
    private LoginDAO loginDAOMock;
    private LoginService loginServiceMock;

    @BeforeEach
    void setup(){
        controller = new LoginController();
        loginDAOMock = mock(LoginDAO.class);
        loginServiceMock = mock(LoginService.class);
    }

    @Test
    void testForValidUser() {
        //Setup
        UserModel userModel = new UserModel();
        userModel.setFullname(USERNAME);
        userModel.setToken("1234-EF");

        controller.setLoginDAO(loginDAOMock);
        controller.setLoginService(loginServiceMock);
        when(loginDAOMock.login(USERNAME,PASSWORD)).thenReturn(userModel);

        LoginRequest login = new LoginRequest();

        login.setUser(USERNAME);
        login.setPassword(PASSWORD);

        //Test
        Response loginResponse = controller.login(login);

        assertEquals(200,loginResponse.getStatus());
    }

    @Test
    void testForInvalidUser(){
        //Setup
        LoginRequest login = new LoginRequest();

        login.setUser("");
        login.setPassword("");

        controller.setLoginDAO(loginDAOMock);
        controller.setLoginService(loginServiceMock);

        when(loginDAOMock.login("","")).thenReturn(new UserModel());

        //Test
        Response loginResponse = controller.login(login);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(),loginResponse.getStatus());

    }




}
