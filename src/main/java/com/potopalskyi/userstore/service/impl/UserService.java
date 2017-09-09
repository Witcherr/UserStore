package com.potopalskyi.userstore.service.impl;

import com.potopalskyi.userstore.dao.TransactionManager;
import com.potopalskyi.userstore.dao.UserDao;
import com.potopalskyi.userstore.entity.User;
import com.potopalskyi.userstore.service.IPhoneService;
import com.potopalskyi.userstore.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private UserDao userDao;
    private IPhoneService phoneService;
    private TransactionManager transactionManager = new TransactionManager();

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void add(User user) {
        try {
            long phoneId = phoneService.add(user.getPhone());
            user.getPhone().setId(phoneId);
            userDao.add(user);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPhoneService(IPhoneService phoneService) {
        this.phoneService = phoneService;
    }
}
