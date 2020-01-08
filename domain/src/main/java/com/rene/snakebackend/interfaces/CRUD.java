package com.rene.snakebackend.interfaces;

public interface CRUD<T,R> {
    T create(T entity);

    T read(R id);

    Iterable<T> readAll();

    T update(T entity);

    boolean delete(R id);
}