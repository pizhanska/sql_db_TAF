package com.mentorship.db.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static com.mentorship.db.utilities.UtilityConstants.*;

/**
 * Created by Uliana Pizhanska on 05/06/2017.
 */
public abstract class BaseDAO <T, K>{

    public abstract List<T> getAll(Connection connection);
    public abstract int update(T entity, int id, Connection connection);
    public abstract T getEntityById(K id, Connection connection);
    public abstract int delete(K id, Connection connection);
    public abstract int create(T entity, Connection connection);



    public Connection returnConnectionInPool() {
        try {
            Class.forName(DB_CLASS).newInstance();
            return DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kind of exception found");
            return null;
        }
    }

    public PreparedStatement getPrepareStatement(String sql ,Connection connection) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
            return ps;
        }

    }

    public void closePrepareStatement (PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
