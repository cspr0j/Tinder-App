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

    public List<User> getAllActive() {
        return usersDAO.getAllActive();
    }

    public boolean delete(int id) {
        return usersDAO.delete(id);
    }
}
