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
    public List<ActorObj> getAll(Connection connection){
        List<ActorObj> actorObjs = new ArrayList();
        statement = getPrepareStatement(SELECT_ALL, connection);
        try {
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                ActorObj actorObj = new ActorObj();
                actorObj.setActorId(Integer.parseInt(resultSet.getString("actor_id")));
                actorObj.setFirstName(resultSet.getString("first_name"));
                actorObj.setLastName(resultSet.getString("last_name"));
                actorObj.setLastUpdate(resultSet.getDate("last_update"));

                actorObjs.add(actorObj);
            }
            resultSet.close();
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
    public int create(ActorObj entity, Connection connection) {
        statement = getPrepareStatement(INSERT_ACTOR,connection);
        try{
            statement.setInt(1,entity.getActorId());
            statement.setString(2,entity.getFirstName());
            statement.setString(3,entity.getLastName());
            statement.setDate(4,getCurrentTimeStamp());
            return statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    @Override
    public int delete(Integer id, Connection connection) {
        statement = getPrepareStatement(DELETE_ACTOR, connection);
        try {
            statement.setInt(1,id);
            return statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    @Override
    public ActorObj getEntityById(Integer id ,Connection connection) {
        ActorObj actorObj = new ActorObj();
        statement = getPrepareStatement(SELECT_BY_ID,connection);
        try {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                actorObj.setActorId(id);
                actorObj.setFirstName(resultSet.getString("first_name"));
                actorObj.setLastName(resultSet.getString("last_name"));
                actorObj.setLastUpdate(resultSet.getDate("last_update"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(statement);
        }
        return actorObj;
    }

    @Override
    public int update(ActorObj entity, int id, Connection connection) {
        statement = getPrepareStatement(UPDATE_ACTOR,connection);
        try{
            statement.setString(1,entity.getFirstName());
            statement.setString(2,entity.getLastName());
            statement.setDate(3,getCurrentTimeStamp());
            statement.setInt(4,id);
            return statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        finally {
            closePrepareStatement(statement);
        }

    }
}
