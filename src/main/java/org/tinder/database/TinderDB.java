package org.tinder.database;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class TinderDB {
    private static final String DB_URL = "jdbc:postgresql://ec2-34-248-169-69.eu-west-1.compute.amazonaws.com:5432/dbd0on8jtum1fm";

    private static final String USERNAME = "zhzkoceyzzvtsg";
    private static final String PASSWORD = "111bcf2952d9e70db0cfa61932e90ec8d06f9d5829cbc57aba4d61b4cf46350b";

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
