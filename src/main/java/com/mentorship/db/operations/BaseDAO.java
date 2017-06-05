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

    private Connection connection;
    public abstract List<T> getAll();
    public abstract int update(T entity);
    public abstract T getEntityById(K id);
    public abstract int delete(K id);
    public abstract int create(T entity);



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

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
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
