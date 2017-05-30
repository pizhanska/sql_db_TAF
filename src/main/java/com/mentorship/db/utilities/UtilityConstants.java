package com.mentorship.db.utilities;

/**
 * Created by Uliana Pizhanska on 30/05/2017.
 */
public final class UtilityConstants {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String PASSWORD = "12345trewq";
    public static final String DB_CLASS = "com.mysql.jdbc.Driver";
    public static final String SELECT_ALL = "SELECT * FROM actor";
    public static final String INSERT_ACTOR = "INSERT INTO actor (actor_id, first_name, last_name, last_update) VALUES (?,?,?,?)";
    public static final String SELECT_BY_ID = "SELECT * FROM actor where actor_id=?";


    private UtilityConstants(){

    }
}
