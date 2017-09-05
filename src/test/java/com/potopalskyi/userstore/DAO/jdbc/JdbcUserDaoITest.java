package com.potopalskyi.userstore.dao.jdbc;

import com.potopalskyi.userstore.entity.User;
import org.junit.Test;

import java.util.List;

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