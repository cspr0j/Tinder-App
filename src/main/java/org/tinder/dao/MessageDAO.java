package org.tinder.dao;

import lombok.RequiredArgsConstructor;
import org.tinder.database.TinderDB;
import org.tinder.entities.Message;

import java.sql.Connection;
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
        return false;
    }

    @Override
    public Message get(Long idTo) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public boolean update(Message message) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
