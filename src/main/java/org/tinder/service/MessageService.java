package org.tinder.service;

import org.tinder.dao.MessageDAO;
import org.tinder.entities.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {

    private final MessageDAO messageDAO;

    public MessageService(Long id) {
        this.messageDAO = new MessageDAO(id);
    }

    public boolean save(Message message) {
        return messageDAO.save(message);
    }

    public List<Message> getAllItemsFromDB() {
        return messageDAO.getAllItemsFromDB();
    }

    public List<Message> getAllItemsByTargetId(Long userOne, Long userTwo) {
        return getAllItemsFromDB().stream()
                .filter(message -> !message.getUserId().equals(message.getTargetId()))
                .filter(message -> message.getTargetId().equals(userOne) || message.getTargetId().equals(userTwo))
                .filter(message -> message.getUserId().equals(userOne) || message.getUserId().equals(userTwo))
                .collect(Collectors.toList());
    }

    public boolean update(Message message) {
        return messageDAO.update(message);
    }

    public boolean deleteAllMessages(Long idTo) {
        return messageDAO.delete(idTo);
    }

    public boolean deleteSelectedMessages(Long idTo, List<Long> idList) {
        return messageDAO.delete(idTo, idList);
    }
}
