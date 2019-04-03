package com.spotitube.dao.login;

import com.spotitube.dao.ConnectionFactory;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    @Inject
    private ConnectionFactory factory;

    public UserModel login(String username, String password){

        var model = new UserModel();
        ResultSet userResult;
        try {
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT U.*, U_TOKEN FROM [USER] U LEFT JOIN TOKEN T ON U.U_NAME = T.U_NAME WHERE U.U_NAME = ? AND U_PASSWORD = ?");
            st.setString(1, username);
            st.setString(2, password);
            userResult = st.executeQuery();

            if(userResult.next()) {
                model.setUser(userResult.getString("U_NAME"));
                model.setToken(userResult.getString("U_TOKEN"));
            }
        }
        catch(SQLException e){
            System.out.println("login error" + e);
        }
        return model;
    }
}
