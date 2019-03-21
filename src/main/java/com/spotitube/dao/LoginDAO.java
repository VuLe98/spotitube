package com.spotitube.dao;

import com.spotitube.User;
import com.spotitube.database.DatabaseRequest;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    @Inject
    private DatabaseRequest request;

    public UserModel login(String username, String password){

        var model = new UserModel();
        ResultSet userResult = null;
        try {
            Connection connection = request.connectToDB();
            PreparedStatement st = connection.prepareStatement("SELECT S.U_NAME, S.U_PASSWORD, S.U_FULLNAME, T.U_TOKEN FROM [USER] S INNER JOIN TOKEN T on S.U_NAME = T.U_NAME WHERE S.U_NAME = ?");
            st.setString(1, username);
            userResult = st.executeQuery();

            while(userResult.next()) {
                if (userResult.getString("U_PASSWORD").equals(password) && userResult.getString("U_NAME").equals(username)){
                    model.setFullname(userResult.getString("U_NAME"));
                    model.setToken(userResult.getString("U_TOKEN"));
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error" + e);
        }
        return model;
    }

    public String getUserByToken(String token){
        ResultSet userResult = null;
        String userName = "";
        try{
            Connection connection = request.connectToDB();
            PreparedStatement st = connection.prepareStatement("SELECT U_NAME FROM TOKEN WHERE U_TOKEN = ?");
            st.setString(1,token);
            userResult = st.executeQuery();

            while(userResult.next()){
                userName = userResult.getString("U_NAME");
            }
        }
        catch(SQLException e){
            System.out.println("Error" + e);
        }
        return userName;
    }
}
