package org.tinder.dao;

import java.util.List;

public interface DAO<T> {

    boolean save(T t);
    T get(Long userId);
    List<T> getAll();
    boolean update(T t);
    boolean delete(Long id);
}
