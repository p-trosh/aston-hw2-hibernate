package ru.trosh.astontrainee.dao;

import java.util.List;

public interface CrudOperations<T, I> {

    T create(T entity);

    T selectById(I id);

    List<T> selectAll();

    T update(T entity);

    void delete(I id);
}