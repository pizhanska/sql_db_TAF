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

    public static java.sql.Date getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    public int insertRecords(Connection connection, String query, int id, String firstName, String lastName ){
        int result = 0;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setString(2,firstName);
            statement.setString(3,lastName);
            statement.setDate(4,getCurrentTimeStamp());
            result = statement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
    public int updateRecord(Connection connection, String query, String firstName, String lastName,int id ){
        int result = 0;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setDate(3,getCurrentTimeStamp());
            statement.setInt(4,id);
            result = statement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public int deleteRecord(Connection connection, String query,int id ){
        int result = 0;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            result = statement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
