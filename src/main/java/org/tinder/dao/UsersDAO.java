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
        final String statement = "INSERT INTO users (email,password, name, surname, photo_url, age, gender)" +
                " VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPhoto_url());
            ps.setInt(6, user.getAge());
            ps.setInt(7, user.getGender());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User get(Long userId) {
        final String statement = "SELECT * FROM users WHERE id = ? AND is_active = ?";
        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setLong(1, userId);
            ps.setBoolean(2, true);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String url = rSet.getString("photo_url");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("is_active");

                user = new User(id, login, password, name, surname, url, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User get(String username) {
        final String statement = "SELECT * FROM users WHERE email = ? AND is_active = ?";
        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setString(1, username);
            ps.setBoolean(2, true);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String url = rSet.getString("photo_url");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("is_active");

                user = new User(id, login, password, name, surname, url, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllItemsFromDB() {
        final String statement = "SELECT * FROM users where is_active = ? ORDER BY id";
        PreparedStatement ps;
        List<User> users = new ArrayList<>();
        try {
            ps = connection.prepareStatement(statement);
            ps.setBoolean(1, true);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {

                users.add(
                        new User(
                                rSet.getLong("id"),
                                rSet.getString("email"),
                                rSet.getString("password"),
                                rSet.getString("name"),
                                rSet.getString("surname"),
                                rSet.getString("photo_url"),
                                rSet.getInt("age"),
                                rSet.getString("gender"),
                                rSet.getBoolean("is_active")
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
        final String statement = "update users set is_active = ? where id = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setBoolean(1, false);
            ps.setLong(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getNotLikedUserV1(Long userId){
        final String statement = "SELECT * FROM users WHERE id =? AND is_active = ?";
        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setLong(1, userId);
            ps.setBoolean(2, true);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String url = rSet.getString("photo_url");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("is_active");

                user = new User(id, login, password, name, surname, url, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getNotLikedUserV2(Long userId){
        final String statement = "SELECT * FROM users " +
                "WHERE is_active = ? AND NOT id = ? " +
                "AND id NOT IN (SELECT l.liked_user_id FROM likes l where user_id = ?) ORDER BY id LIMIT 1";

        User user = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setBoolean(1, true);
            ps.setLong(2, userId);
            ps.setLong(3, userId);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long id = rSet.getLong("id");
                String login = rSet.getString("email");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String url = rSet.getString("photo_url");
                Integer age = rSet.getInt("age");
                String gender = rSet.getString("gender");
                boolean isActive = rSet.getBoolean("is_active");

                user = new User(id, login, password, name, surname, url, age, gender, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
