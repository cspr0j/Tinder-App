package org.tinder.dao;

import java.util.List;

public interface DAO<T> {

    default boolean save(T t) {
        throw new RuntimeException("Method not allowed");
    }

    default T get(Long userId) {
        throw new RuntimeException("Method not allowed");
    }

    default List<T> getAllItems() {
        throw new RuntimeException("Method not allowed");
    }

    default boolean update(T t) {
        throw new RuntimeException("Method not allowed");
    }

    default boolean delete(Long id) {
        throw new RuntimeException("Method not allowed");
    }
}
