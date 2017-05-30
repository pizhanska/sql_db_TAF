package com.mentorship.db.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class CRUDImpl {

    private ResultSet resultSet = null;
    private PreparedStatement statement = null;

    public ResultSet selectRecords(Connection connection, String query, int id){
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet selectRecords(Connection connection, String query){
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return resultSet;
    }

    public  java.sql.Date getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }
}
