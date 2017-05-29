package com.mentorship.db.test;

import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class TestSQLDB {
    private Connection connection;
    private Statement statement ;
    private final String DB_URL = "jdbc:mysql://localhost:3310/user";
    private final String DB_USER = "root";
    private final String PASSWORD = "12345trewq";
    private final String DB_CLASS = "com.mysql.jdbc.Driver";

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
}
