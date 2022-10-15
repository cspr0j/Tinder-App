package org.tinder.dao;

import org.tinder.database.TinderDB;
import org.tinder.entities.Like;

import java.sql.Connection;
import java.util.List;

public class MessageDAO implements DAO<Like> {
    private static final Connection connection;

    static {
        connection = TinderDB.connectToDB();
    }


    @Override
    public boolean save(Like like) {
        return false;
    }

    @Override
    public Like get(String string) {
        return null;
    }

    @Override
    public List<Like> getAllActive() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
