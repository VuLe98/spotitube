package com.spotitube.scratchTets;

import com.spotitube.controller.login.LoginController;
import com.spotitube.dao.login.LoginDAO;
import com.spotitube.dto.LoginRequest;
import com.spotitube.models.UserModel;

import javax.ws.rs.core.Response;

public class LoginControllerTets {

    public static final String VU = "Vu";
    public static final String WACHTWOORD = "wachtwoord";

//    @Test
//    void testForValidUser(){
//        //setup
//        LoginController controller = new LoginController();
//
//        UserModel userModel = new UserModel();
//        userModel.setFullname();
//
//        LoginDAO loginDAO = Mockito.mock(LoginDAO.class);
//        Mockito.when(loginDAO.login(VU,WACHTWOORD)).thenReturn(userModel);
//        LoginRequest login = new LoginRequest();
//
//        controller.setLoginDAO(loginDAO);
//
//        login.setUser(VU);
//        login.setPassword(WACHTWOORD);
//
//        //Test
//        Response loginResponse = controller.login(login);
//
//        Assertions.assertEquals(200,loginResponse.getStatus());

    //loginController.doSmart(1);
    //Mockito.verify(loginDAO).doSmart(38)
//
//
//    }
}
