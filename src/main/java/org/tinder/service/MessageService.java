package org.tinder.service;

import lombok.AllArgsConstructor;
import org.tinder.dao.MessageDAO;
import org.tinder.entities.Message;

import java.util.List;


public class MessageService {
    private final MessageDAO messageDAO;

    public MessageService(Long id) {
        messageDAO = new MessageDAO(id);
    }

    public boolean save(Message message) {
        return messageDAO.save(message);
    }

    // TODO delete if it is not necessary
//    public Message get(Long idTo) {
//        return messageDAO.get(idTo);
//    }

    public List<Message> getAllItemsFromDB() {
        return messageDAO.getAllItemsFromDB();
    }

    public List<Message> getAllItemsByTargetId(Long targetId) {
        return messageDAO.getAllItemsByTargetId(targetId);
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
