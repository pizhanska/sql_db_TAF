package com.mentorship.db.test;

import com.mentorship.db.model.ActorObj;
import com.mentorship.db.operations.ActorDAO;import com.mentorship.db.utilities.ReadWriteObjFile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.mentorship.db.utilities.UtilityConstants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class TestSQLDB {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private ActorDAO actorDAO = null;
    private ActorObj actorObj = null;

    @BeforeClass
    public void createConnection(){
        try {
            actorDAO = new ActorDAO();
            connection = actorDAO.returnConnectionInPool();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kind of exception found");
        }
    }


    @Test(priority = 1)
    public void readAll(){
        List<ActorObj> objs = new ArrayList() ;
        objs = actorDAO.getAll(connection);
        Assert.assertNotNull(objs);
        for(ActorObj actorObj : objs){
            System.out.println(actorObj.toString());
        }
    }


    @Test(priority = 2)
    public void readById(){
        ActorObj obj= new ActorObj();
        obj = actorDAO.getEntityById(301,connection);
        Assert.assertNotNull(obj);
        Assert.assertEquals(obj.getActorId() ,301);
    }


    @Test(priority = 3)
    public void insertActor(){
        ActorObj obj = new ActorObj(545,"volodymyr", "demmmchyk");
        int res = actorDAO.create(obj,connection);
        Assert.assertEquals(res,1);
        Assert.assertNotNull(actorDAO.getEntityById(545,connection));
    }


    @Test(priority = 4)
    public void updateActor(){
        ActorObj obj = new ActorObj(545, "volodymyr", "besh");
        int result = actorDAO.update(obj,545,connection);
        Assert.assertEquals(result,1);
    }

    @Test(priority = 5)
    public void deleteActor(){
        int result = actorDAO.delete(545,connection);
        Assert.assertEquals(result,1);
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
