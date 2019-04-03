package com.spotitube.dao.token;

import com.spotitube.dao.ConnectionFactory;
import com.spotitube.models.UserModel;

import javax.inject.Inject;
import java.sql.*;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory factory;

    public UserModel createTokenForUser(String username){
        var user = new UserModel();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO TOKEN VALUES (?,?)");

            String userToken = UUID.randomUUID().toString();

            st.setString(1,username);
            st.setString(2,userToken);
            st.execute();

            user.setUser(username);
            user.setToken(userToken);

        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserModel updateTokenForUser(String username){
        var user = new UserModel();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement st = connection.prepareStatement("UPDATE TOKEN SET U_TOKEN = ? WHERE U_NAME = ?");

            String userToken = UUID.randomUUID().toString();

            st.setString(1,userToken);
            st.setString(2,username);
            st.execute();

            user.setUser(username);
            user.setToken(userToken);

        }
        catch(SQLException e){
            throw new RuntimeException(e);
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
