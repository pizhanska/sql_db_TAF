package com.mentorship.db.operations;

import com.mentorship.db.model.ActorObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.mentorship.db.utilities.UtilityConstants.*;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public class ActorDAO extends BaseDAO<ActorObj,Integer>{

    private ResultSet resultSet = null;
    private PreparedStatement statement = null;


    @Override
    public List<ActorObj> getAll(){
        List<ActorObj> actorObjs = new ArrayList();
        statement = getPrepareStatement(SELECT_ALL);
        try {
            resultSet = statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(statement);
        }
        return actorObjs;
    }

    public static java.sql.Date getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    @Override
    public int create(ActorObj entity) {
        return 1;
    }

    @Override
    public int delete(Integer id) {
        int result = 0;
        statement = getPrepareStatement(DELETE_ACTOR);
        try {
            statement.setInt(1,id);
            result = statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ActorObj getEntityById(Integer id) {
        return null;
    }

    @Override
    public int update(ActorObj entity) {
        return 0;
    }
}
