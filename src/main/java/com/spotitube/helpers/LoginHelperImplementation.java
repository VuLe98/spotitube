package com.spotitube.helpers;

import com.spotitube.dao.LoginDAO;
import com.spotitube.dao.TokenDAO;

import javax.inject.Inject;

public class LoginHelperImplementation  {

    @Inject
    public LoginDAO dao;

    @Inject
    public TokenDAO tokenDAO;
//
//    @Override
//    public Token login(User user) throws LoginException{
//        Token tok;
//        try {
//            ResultSet newUser = dao.getUser(user.getUsername(),user.getPassword());
//            if (newUser != null && newUser.getString("U_PASSWORD").equals(user.getPassword())){
//                tok = tokenDAO.createTokenForUser(newUser.getString("U_NAME"));
//            } else{
//                throw new LoginException();
//            }
//        }
//        catch(LoginException l){
//            System.out.println("Invalid login");
//        }
//        catch(SQLException e){
//            System.out.println("Error: " + e);
//        }
//    }

}
