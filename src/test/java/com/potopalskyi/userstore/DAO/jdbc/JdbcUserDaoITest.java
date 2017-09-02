package com.potopalskyi.userstore.DAO.jdbc;

import com.potopalskyi.userstore.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcUserDaoITest {

    @Test
    public void testGetAll() throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> userList = jdbcUserDao.getAll();

        for(User user: userList){
            System.out.println(user);
        }
    }
}