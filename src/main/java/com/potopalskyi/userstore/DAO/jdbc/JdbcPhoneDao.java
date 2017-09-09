package com.potopalskyi.userstore.dao.jdbc;

import com.potopalskyi.userstore.dao.ConnectionInstance;
import com.potopalskyi.userstore.dao.PhoneDao;
import com.potopalskyi.userstore.entity.Phone;

import java.sql.*;
import java.util.List;

public class JdbcPhoneDao implements PhoneDao {
    private final static String SQL_QUERY_SAVE_PHONE = "insert into phones(countryCode, innerNumber, userId) values(?, ?, ?)";
    private Connection connection;

    public JdbcPhoneDao() {
        connection = ConnectionInstance.getInstance();
    }

    @Override
    public long add(Phone phone) {
        long phoneId;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_SAVE_PHONE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, phone.getCountryCode());
            preparedStatement.setString(2, phone.getInnerNumber());
            preparedStatement.setLong(3, phone.getUserId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                phoneId =resultSet.getLong(1);
                return phoneId;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
