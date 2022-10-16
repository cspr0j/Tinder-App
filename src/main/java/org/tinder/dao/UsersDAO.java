package org.tinder.dao;

import org.tinder.database.TinderDB;
import org.tinder.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements DAO<User> {

    private static final Connection connection;

    static {
        connection = TinderDB.connectToDB();
    }

    @Override
    public boolean save(User user) {
        final String statement = "INSERT INTO users (email,password, name, surname, age, gender) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setInt(5, user.getAge());
            ps.setInt(6, user.getGender());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User get(Long userId) {
        final String statement = "SELECT * FROM users WHERE id = ?";
        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setLong(1, userId);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("active");

                user = new User(id, login, password, name, surname, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User get(String username) {
        final String statement = "SELECT * FROM users WHERE email = ?";
        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setString(1, username);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("active");

                user = new User(id, login, password, name, surname, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        final String statement = "SELECT * FROM users where active = true";
        PreparedStatement ps;
        List<User> users = new ArrayList<>();
        try {
            ps = connection.prepareStatement(statement);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {

                users.add(
                        new User(
                                rSet.getLong("id"),
                                rSet.getString("email"),
                                rSet.getString("password"),
                                rSet.getString("name"),
                                rSet.getString("surname"),
                                rSet.getInt("age"),
                                rSet.getString("gender"),
                                rSet.getBoolean("active")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final String statement = "update users set active = ? where id = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setBoolean(1,false);
            ps.setLong(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
