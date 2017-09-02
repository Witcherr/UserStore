package com.potopalskyi.userstore.DAO;

import java.util.List;

public interface GenericDao<T> {
    List<T> getAll();

    void add(T t);
}
