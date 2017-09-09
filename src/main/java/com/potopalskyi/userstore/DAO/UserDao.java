package com.potopalskyi.userstore.dao;

import com.potopalskyi.userstore.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<User> getUsersByInnerCode(String innerCode);
}
