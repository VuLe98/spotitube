package com.spotitube.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseRequest {
    Connection cnSpot = null;

    public Connection connectToDB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            cnSpot = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Spotitube_Database","sa","Foetbalvu1998");
        }
        catch(SQLException e){
            System.out.println("Error connecting to a com.spotitube.database" + e);
        }

        return cnSpot;
    }


}
