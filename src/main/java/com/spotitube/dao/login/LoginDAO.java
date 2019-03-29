package com.spotitube.dao.login;

import com.spotitube.dao.token.TokenDAO;
import com.spotitube.entities.Token;
import com.spotitube.database.ConnectionFactory;
import com.spotitube.entities.User;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    @Inject
    private ConnectionFactory factory;

    private TokenDAO tDao;

    @Inject
    public void setTokenDAO(TokenDAO tDao){
        this.tDao = tDao;
    }

    public UserModel login(String username, String password){

        var model = new UserModel();
        ResultSet userResult = null;
        try {
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM [USER] WHERE U_NAME = ? AND U_PASSWORD = ?");
            st.setString(1, username);
            st.setString(2, password);
            userResult = st.executeQuery();

            if(userResult.next()) {
                model.setFullname(userResult.getString("U_NAME"));
                tDao.deleteTokenForUser(username);
                Token newToken = tDao.createTokenForUser(username);
                model.setToken(newToken.getToken());
            }
        }
        catch(SQLException e){
            System.out.println("login error" + e);
        }
        return model;
    }

    public User getUser(String username, String password){
        var user = new User();
        ResultSet userResult = null;
        try{
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM [USER] WHERE U_NAME = ?");
            st.setString(1, username);
            userResult = st.executeQuery();

            if(userResult.next()){
                String dbUser = userResult.getString("U_NAME");
                String dbPassword = userResult.getString("U_PASSWORD");

                if(password.equals(dbPassword) && username.equals(dbUser)){
                    user.setUser(dbUser);
                    user.setPassword(dbPassword);
                }
            }
        }
        catch(SQLException e){
            System.out.println("getUser error" + e);
        }
        return user;
    }


    public String getUserByToken(String token){
        ResultSet userResult = null;
        String userName = "";
        try{
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT U_NAME FROM TOKEN WHERE U_TOKEN = ?");
            st.setString(1,token);
            userResult = st.executeQuery();

            while(userResult.next()){
                userName = userResult.getString("U_NAME");
            }
        }
        catch(SQLException e){
            System.out.println("getUserByToken error" + e);
        }
        return userName;
    }
}
