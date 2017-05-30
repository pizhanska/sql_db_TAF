package com.mentorship.db.test;

import com.mentorship.db.operations.CRUDImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.mentorship.db.utilities.UtilityConstants.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class TestSQLDB {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private CRUDImpl crud ;

    @BeforeClass
    public void createConnection(){
        try {
            Class.forName(DB_CLASS).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
            crud = new CRUDImpl();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kind of exception found");
        }
    }


    @Test(priority = 1)
    public void readAll(){
        resultSet = crud.selectRecords(connection,SELECT_ALL);
        Assert.assertNotNull(resultSet);
        try {
            Assert.assertEquals(resultSet.getMetaData().getColumnCount(), 4);
            Assert.assertEquals("actor_id",resultSet.getMetaData().getColumnName(1));
            Assert.assertEquals("first_name",resultSet.getMetaData().getColumnName(2));
            Assert.assertEquals("last_name",resultSet.getMetaData().getColumnName(3));
            Assert.assertEquals("last_update",resultSet.getMetaData().getColumnName(4));

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+ " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getString(4) + "\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Test(priority = 2)
    public void readById(){
        resultSet = crud.selectRecords(connection, SELECT_BY_ID, 301);
        Assert.assertNotNull(resultSet);
        try {
            Assert.assertEquals(resultSet.getMetaData().getColumnCount(), 4);
            Assert.assertEquals("actor_id",resultSet.getString(1));
            Assert.assertEquals("first_name",resultSet.getString(2));
            Assert.assertEquals("last_name",resultSet.getString(3));
            Assert.assertEquals("last_update",resultSet.getString(4));

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+ " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getString(4) + "\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Test(priority = 3)
    public void insertActor(){
        int result = crud.insertRecords(connection,INSERT_ACTOR,333, "volodymyr","demchyk");
        Assert.assertEquals(result,1);
        Assert.assertNotNull(crud.selectRecords(connection,SELECT_BY_ID, 333));
    }


    @Test(priority = 4)
    public void updateActor(){
        int result = crud.updateRecord(connection,UPDATE_ACTOR,"volodymyr","demchyk1111", 333);
        Assert.assertEquals(result,1);
        resultSet = crud.selectRecords(connection, SELECT_BY_ID, 333);
        try {
            resultSet.next();
            Assert.assertEquals(resultSet.getString(3), "demchyk1111");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test(priority = 5)
    public void deleteActor(){
        int result = crud.deleteRecord(connection,DELETE_ACTOR, 333);
        Assert.assertEquals(result,1);
        try {
          Assert.assertEquals(crud.selectRecords(connection, SELECT_BY_ID, 333).next(), false);
        }
        catch (SQLException e){
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
