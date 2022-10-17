package org.tinder.dao;

import lombok.RequiredArgsConstructor;
import org.tinder.database.TinderDB;
import org.tinder.entities.Message;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MessageDAO implements DAO<Message> {

    private final Long idFrom;
    private static final Connection connection;

    static {
        connection = TinderDB.connectToDB();
    }

    @Override
    public boolean save(Message message) {
        final String statement = "INSERT INTO messages (user_id, target_id, message, date) VALUES (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setLong(1, message.getUserId());
            ps.setLong(2, message.getTargetId());
            ps.setString(3, message.getText());
            ps.setTimestamp(4, message.getDate());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Message get(Long idTo) {
        Message message = null;
        final String statement = "SELECT * FROM messages WHERE user_id = ? AND target_id = ? ORDER BY date DESC limit 1";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ps.setLong(2, idTo);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long userId = rSet.getLong("user_id");
                Long likedUserId = rSet.getLong("target_id");
                String text = rSet.getString("message");
                Timestamp date = rSet.getTimestamp("date");
                message = new Message(userId, likedUserId, text, date);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        final String statement = "SELECT * FROM messages WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                messages.add(new Message(
                        rSet.getLong("user_id"),
                        rSet.getLong("target_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    @Override
    public boolean update(Message message) {
        return false;
    }

    @Override
    public boolean delete(Long idTo) {
        final String statement = "DELETE FROM messages WHERE user_id = ? AND target_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ps.setLong(2, idTo);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
