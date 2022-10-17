package org.tinder.service;

import lombok.AllArgsConstructor;
import org.tinder.dao.LikeDAO;
import org.tinder.entities.Like;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LikeService {

    private final LikeDAO likeDAO;

    public boolean save(Like like) {
        return likeDAO.save(like);
    }


    public Like get(Long idTo) {
        return likeDAO.get(idTo);
    }

    public List<Like> getAllItemsFromDB() {
        return likeDAO.getAllItemsFromDB();
    }

    public boolean update(Like like) {
        return likeDAO.update(like);
    }

    public boolean delete(Long idTo) {
        return likeDAO.delete(idTo);
    }
}
