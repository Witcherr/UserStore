package com.potopalskyi.userstore.dao.jdbc;

import org.junit.Test;

public class JdbcPhoneDaoTest {

    @Test
    public void testGetMaxId() throws Exception {
        JdbcPhoneDao jdbcPhoneDao = new JdbcPhoneDao();

        System.out.println(jdbcPhoneDao.getMaxId());
    }
}