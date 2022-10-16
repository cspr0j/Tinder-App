package org.tinder.dao;

import lombok.RequiredArgsConstructor;
import org.tinder.database.TinderDB;
import org.tinder.entities.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LikeDAO implements DAO<Like> {

    private final Long idFrom;
    private static final Connection connection;

    static {
        connection = TinderDB.connectToDB();
    }

    @Override
    public boolean save(Like like) {
        final String statement = "INSERT INTO likes (user_id, liked_user_id) VALUES (?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(statement);
            ps.setLong(1, like.getUserId());
            ps.setLong(2, like.getLikedUserId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Like get(Long idTo) {
        Like like = null;
        final String statement = "SELECT * FROM likes WHERE user_id = ? AND liked_user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ps.setLong(2,idTo);
            ResultSet rSet = ps.executeQuery();

            if (rSet.next()) {
                Long userId = rSet.getLong("user_id");
                Long likedUserId = rSet.getLong("liked_user_id");
                like = new Like(userId,likedUserId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    @Override
    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        final String statement ="SELECT * FROM likes WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setLong(1, idFrom);
            ResultSet rSet = ps.executeQuery();

            while (rSet.next()) {
                likes.add(new Like(
                        rSet.getLong("userId"),
                        rSet.getLong("liked")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }

    @Override
    public boolean update(Like like) {
        return false;
    }

    @Override
    public boolean delete(Long idTo) {
        final String statement = "DELETE FROM likes WHERE user_id = ? AND liked_user_id = ?";

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
