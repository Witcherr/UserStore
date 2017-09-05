package com.potopalskyi.userstore.service.impl;

import com.potopalskyi.userstore.dao.UserDao;
import com.potopalskyi.userstore.entity.User;
import com.potopalskyi.userstore.service.IPhoneService;
import com.potopalskyi.userstore.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private UserDao userDao;
    private IPhoneService phoneService;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void add(User user) {
        phoneService.add(user.getPhone());
        long maxId = phoneService.getMaxId();
        user.getPhone().setId(maxId);
        userDao.add(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPhoneService(IPhoneService phoneService) {
        this.phoneService = phoneService;
    }
}
