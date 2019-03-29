package com.spotitube.dao.token;

import com.spotitube.entities.Token;
import com.spotitube.database.ConnectionFactory;

import javax.inject.Inject;
import java.sql.*;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory request;

    public Token createTokenForUser(String username){
        var token = new Token();

        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO TOKEN VALUES (?,?)");

            String userToken = UUID.randomUUID().toString();

            st.setString(1,username);
            st.setString(2,userToken);
            st.execute();

            token.setUser(username);
            token.setToken(userToken);

        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return token;
    }

    public void deleteTokenForUser(String username){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("DELETE FROM TOKEN WHERE U_NAME = ?");

            st.setString(1,username);
            st.execute();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Token getToken(String token){
        Token verkrijgToken = null;
        ResultSet resultSet;
        try{
            Connection connection = request.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TOKEN WHERE U_TOKEN = ?");
            preparedStatement.setString(1,token);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String dbToken = resultSet.getString("U_TOKEN");
                String user = resultSet.getString("U_NAME");
                verkrijgToken = new Token(user,dbToken);
            }
        }
        catch(SQLException e){
            System.out.println("getToken error: " + e);
        }
        return verkrijgToken;
    }
}
