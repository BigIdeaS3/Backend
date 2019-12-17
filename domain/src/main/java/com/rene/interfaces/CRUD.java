package com.rene.interfaces;

import java.util.List;

public interface CRUD<T,R> {
    T create(T entity);

    T read(R id);

    Iterable<T> readAll();

    T update(T entity);

    boolean delete(R id);
}