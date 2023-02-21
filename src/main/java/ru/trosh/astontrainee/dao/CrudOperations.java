package ru.trosh.astontrainee.dao;

import java.util.List;

public interface CrudOperations<T, I> {

    void create(T entity);

    T selectById(I id);

    List<T> selectAll();

    void update(T entity);

    void delete(I id);
}