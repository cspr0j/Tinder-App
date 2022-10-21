package org.tinder.dao;

import lombok.RequiredArgsConstructor;
import org.tinder.database.TinderDB;
import org.tinder.entities.Message;

import java.sql.*;
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

    // TODO delete if it is not necessary
//    @Override
//    public Message get(Long idTo) {
//        Message message = null;
//        final String statement = "SELECT * FROM messages WHERE message_id = ?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(statement);
//            ps.setLong(1, idTo);
//            ResultSet rSet = ps.executeQuery();
//
//            if (rSet.next()) {
//                Long messageId = rSet.getLong("message_id");
//                Long userId = rSet.getLong("user_id");
//                Long likedUserId = rSet.getLong("target_id");
//                String text = rSet.getString("message");
//                Timestamp date = rSet.getTimestamp("date");
//                message = new Message(messageId, userId, likedUserId, text, date);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return message;
//    }

    @Override
    public List<Message> getAllItemsFromDB() {
        List<Message> messages = new ArrayList<>();
        final String statement = "SELECT * FROM messages WHERE is_deleted = ?";
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
                        rSet.getTimestamp("date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> getAllItemsByTargetId(Long targetId) {
        List<Message> messages = new ArrayList<>();
        final String statement = "SELECT * FROM messages WHERE user_id = ? AND target_id = ? AND is_deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ps.setLong(2, targetId);
            ps.setBoolean(3, false);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                messages.add(new Message(
                        rSet.getLong("message_id"),
                        rSet.getLong("user_id"),
                        rSet.getLong("target_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("date")
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
    @Override
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
