package com.revature.api.daos;

import java.util.List;

public interface CrudDAO<T> {

    List<T> getAll();

    T getById(String id);

    void save(T newObject);

    void update(T updatedObject);

    void deleteById(String id);
}