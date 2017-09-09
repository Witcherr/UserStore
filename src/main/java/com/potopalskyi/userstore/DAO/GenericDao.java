package com.potopalskyi.userstore.dao;

import java.util.Collections;
import java.util.List;

public interface GenericDao<T> {
    default List<T> getAll(){return Collections.EMPTY_LIST;};

    long add(T t);
}
