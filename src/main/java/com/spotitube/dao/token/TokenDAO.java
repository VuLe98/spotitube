package com.spotitube.dao.token;

import com.spotitube.entities.Token;
import com.spotitube.database.ConnectionFactory;

import javax.inject.Inject;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory request;

    public Token createTokenForUser(String username){
        var token = new Token();
        ResultSet resultSet = null;

        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO TOKEN VALUES (?,?,?)");

            //Verkrijg de verstrijkdatum (vandaag + 1)
            Calendar cal = Calendar.getInstance();
            Timestamp stamp = new Timestamp(new Date().getTime());
            cal.setTimeInMillis(stamp.getTime());
            cal.add(Calendar.HOUR,24);
            stamp = new Timestamp(cal.getTime().getTime());

            String userToken = UUID.randomUUID().toString();

            st.setString(1,username);
            st.setString(2,userToken);
            st.setTimestamp(3,stamp);
            st.execute();

            token.setUser(username);
            token.setToken(userToken);

        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return token;
    }

    public boolean isTokenValid(Token token){
        boolean isValid = false;
        ResultSet resultSet = null;

        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM TOKEN WHERE U_NAME = ? AND U_TOKEN = ?");
            st.setString(1, token.getUser());
            st.setString(2,token.getToken());
            resultSet = st.executeQuery();

            LocalDateTime now = LocalDateTime.now();

            while(resultSet.next()){
                Date exDate = resultSet.getDate("EXPIRY_DATE");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = dateFormat.format(exDate);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime expiryDate = LocalDateTime.parse(strDate,formatter);
                if(expiryDate.isAfter(now)){
                    isValid = true;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error" + e);
        }
        return isValid;
    }

    public Token getToken(String token){
        Token verkrijgToken = null;
        ResultSet resultSet = null;
        try{
            Connection connection = request.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TOKEN WHERE U_TOKEN = ?");
            preparedStatement.setString(1,token);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String dbToken = resultSet.getString("U_TOKEN");
                String user = resultSet.getString("U_NAME");
                verkrijgToken = new Token(user,dbToken);
            }
        }
        catch(SQLException e){
            System.out.println("Error" + e);
        }
        return verkrijgToken;
    }
}
