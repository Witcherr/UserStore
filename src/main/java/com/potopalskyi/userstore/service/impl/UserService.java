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
            long userId = userDao.add(user);
            for (int i = 0; i < user.getPhoneList().size(); i++) {
                user.getPhoneList().get(i).setUserId(userId);
                phoneService.add(user.getPhoneList().get(i));
            }
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        }
    }

    @Override
    public void delete(long id) {
        try{
            userDao.delete(id);
            phoneService.delete(id);
            transactionManager.commit();
        }catch (Exception e){
            transactionManager.rollback();
        }
    }

    @Override
    public List<User> getUsersByInnerCode(String innerCode) {
        return userDao.getUsersByInnerCode(innerCode);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPhoneService(IPhoneService phoneService) {
        this.phoneService = phoneService;
    }
}
