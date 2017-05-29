package com.mentorship.db.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class TestSQLDB {
    private Connection connection;
    private Statement statement ;
    private final String DB_URL = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String DB_USER = "root";
    private final String PASSWORD = "12345trewq";
    private final String DB_CLASS = "com.mysql.jdbc.Driver";
    private ResultSet resultSet;

    @BeforeClass
    public void createConnection(){
        try {
            Class.forName(DB_CLASS).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
            statement = connection.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kind of exception found");
        }
    }


    @Test
    public void readAll(){
        try {
            resultSet = statement.executeQuery("select * from actor");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown(){
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Can't close connection");
        }
    }
}
