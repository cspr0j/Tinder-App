package org.tinder.service;

import lombok.AllArgsConstructor;
import org.tinder.dao.UsersDAO;
import org.tinder.entities.User;

import java.util.List;

@AllArgsConstructor
public class UserService {
    private final UsersDAO usersDAO = new UsersDAO();

    public boolean save(User user) {
        return usersDAO.save(user);
    }

    public User get(String username) {
        return usersDAO.get(username);
    }

    public User get(Long id) {
        return usersDAO.get(id);
    }

    public User getNotLikedUser(Long id) {
        return usersDAO.getNotLikedUser(id);
    }

    public List<User> getAllActive() {
        return usersDAO.getAllItemsFromDB();
    }

    public boolean update(User user) {
        return usersDAO.update(user);
    }

    public boolean delete(Long id) {
        return usersDAO.delete(id);
    }
}
