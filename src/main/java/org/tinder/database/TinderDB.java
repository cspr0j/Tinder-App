package org.tinder.database;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class TinderDB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Tinder";

    //TODO: change if yours is different
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    public static Connection connectToDB() {
        if (connection != null) {
            //TODO: replace with exception
            return null;
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
