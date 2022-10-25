package org.tinder.dao;

import lombok.RequiredArgsConstructor;
import org.tinder.database.TinderDB;
import org.tinder.entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MessageDAO implements DAO<Message> {

    private static final Connection connection;

    static {
        connection = TinderDB.connectToDB();
    }

    private final Long idFrom;

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
    public List<Message> getAllItems() {
        List<Message> messages = new ArrayList<>();
        final String statement = "SELECT * FROM messages WHERE is_deleted = ? ORDER BY date";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setBoolean(1, false);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                messages.add(new Message(
                        rSet.getLong("message_id"),
                        rSet.getLong("user_id"),
                        rSet.getLong("target_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("date"),
                        rSet.getBoolean("is_deleted")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public boolean update(Message message) {
        return false;
    }

    /**
     * Deletes all messages of these users
     */
    @Override
    public boolean delete(Long idTo) {
        final String statement = "UPDATE messages SET is_deleted = ? WHERE user_id = ? AND target_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setBoolean(1, true);
            ps.setLong(2, idFrom);
            ps.setLong(3, idTo);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Deletes selected messages
     */
    public boolean delete(Long idTo, List<Long> idList) {
        final String statement = "UPDATE messages SET is_deleted = ? WHERE user_id = ?" +
                " AND target_id = ? AND message_id = Any (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            Array ids = connection.createArrayOf("bigint", idList.toArray());
            ps.setBoolean(1, true);
            ps.setLong(2, idFrom);
            ps.setLong(3, idTo);
            ps.setArray(4, ids);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
