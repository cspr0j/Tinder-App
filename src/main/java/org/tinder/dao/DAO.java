package org.tinder.dao;

import java.util.List;

public interface DAO<T> {

    boolean save(T t);

    T get(String string);

    List<T> getAllActive();

    boolean delete(int id);
}
