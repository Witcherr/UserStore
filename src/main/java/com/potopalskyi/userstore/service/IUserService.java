package com.potopalskyi.userstore.service;

import com.potopalskyi.userstore.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    void add(User user);

    void delete(long id);
}
