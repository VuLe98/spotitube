package com.spotitube;

import com.spotitube.controller.LoginController;
import com.spotitube.dto.LoginRequest;
import com.spotitube.services.login.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private static final String USERNAME = "Vu";
    private static final String PASSWORD = "1234";

    private LoginController controller;
    private LoginServiceImpl loginServiceImplMock;
    private LoginRequest validLogin;

    @BeforeEach
    void setup(){
        controller = new LoginController();
        loginServiceImplMock = mock(LoginServiceImpl.class);
        controller.setLoginService(loginServiceImplMock);
        validLogin = new LoginRequest();
        validLogin.setUser(USERNAME);
        validLogin.setPassword(PASSWORD);
    }

    @Test
    void doesLoginControllerInteractWithService(){
        when(loginServiceImplMock.login(validLogin.getUser(),validLogin.getPassword())).thenReturn(Response.ok().build());

        Response actual = controller.login(validLogin);

        assertEquals(200, actual.getStatus());
    }

}
