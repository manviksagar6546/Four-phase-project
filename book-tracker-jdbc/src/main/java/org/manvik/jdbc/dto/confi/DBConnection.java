package org.manvik.jdbc.dto.confi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/personal_library";
    private static final String user = "root";
    private static final String password = "0000";


    // Connects to the database using the defined URL, username, and password
    /**
     * Establishes and returns a new active connection to the MySQL database.
     *
     * @return Connection object to execute SQL queries.
     * @throws SQLException if a database access error occurs or credentials are invalid.
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection( url, user, password );
    }

}
