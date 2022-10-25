package org.tinder.database;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class TinderDB {
    private static final String DB_URL = "jdbc:postgresql://ec2-54-228-32-29.eu-west-1.compute.amazonaws.com:5432/d1g151j8kpi17b";
    private static final String USERNAME = "ttdqdxwwcdkaay";
    private static final String PASSWORD = "bf75da1d28445b29dc7ae648114302eb890c641a62345f6ba94318c44f64e781";
    private static Connection connection;

    public static Connection connectToDB() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
