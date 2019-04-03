package com.spotitube;

import com.spotitube.controller.LoginController;
import com.spotitube.dao.LoginDAO;
import com.spotitube.dao.TokenDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.models.UserModel;
import com.spotitube.services.login.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {

    private static final String USERNAME = "Vu";
    private static final String PASSWORD = "1234";

    private LoginController controller;
    private LoginDAO loginDAOMock;
    private TokenDAO tokenDAOMock;
    private LoginServiceImpl loginServiceImplMock;
    private UserModel userWithToken;

    @BeforeEach
    void setup(){
        controller = new LoginController();
        loginServiceImplMock = new LoginServiceImpl();
        loginDAOMock = mock(LoginDAO.class);
        tokenDAOMock = mock(TokenDAO.class);
        loginServiceImplMock.setLoginDAO(loginDAOMock);
        loginServiceImplMock.setTokenDAO(tokenDAOMock);
        userWithToken = new UserModel(USERNAME,"1234-EF");
    }

    @Test
    void testForValidUser() {
        //Setup
        LoginRequest validLogin = new LoginRequest();

        validLogin.setUser(USERNAME);
        validLogin.setPassword(PASSWORD);

        when(loginDAOMock.login(USERNAME,PASSWORD)).thenReturn(userWithToken);

        //Test
        Response loginResponse = loginServiceImplMock.login(validLogin.getUser(), validLogin.getPassword());

        assertEquals(200,loginResponse.getStatus());
    }

    @Test
    void testForInvalidUser(){
        //Setup
        LoginRequest invalidLogin = new LoginRequest();

        invalidLogin.setUser("");
        invalidLogin.setPassword("");

        when(loginDAOMock.login(invalidLogin.getUser(),invalidLogin.getPassword())).thenReturn(new UserModel());

        //Test
        Response loginResponse = loginServiceImplMock.login(invalidLogin.getUser(),invalidLogin.getPassword());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(),loginResponse.getStatus());
    }
}
