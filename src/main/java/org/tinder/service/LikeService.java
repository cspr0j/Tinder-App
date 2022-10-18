package org.tinder.service;

import lombok.AllArgsConstructor;
import org.tinder.dao.LikeDAO;
import org.tinder.entities.Like;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LikeService {

    private final LikeDAO likeDAO;

    public boolean save(Like like) {
        return likeDAO.save(like);
    }


    public Like get(Long idTo) {
        return likeDAO.get(idTo);
    }

    public List<Like> getAllLikes() {
        return likeDAO.getAllItemsFromDB();
    }
    public List<Long> getAllLikesId() {
        return getAllLikes()
                .stream()
                .map(Like::getLikedUserId)
                .collect(Collectors.toList());
    }

    public boolean update(Like like) {
        return likeDAO.update(like);
    }

    public boolean delete(Long idTo) {
        return likeDAO.delete(idTo);
    }
}
