package com.potopalskyi.userstore.DAO.jdbc;

import com.potopalskyi.userstore.DAO.ConnectionInstance;
import com.potopalskyi.userstore.DAO.PhoneDao;
import com.potopalskyi.userstore.entity.Phone;

import java.sql.*;
import java.util.List;

public class JdbcPhoneDao implements PhoneDao {
    private final static String SQL_QUERY_SAVE_PHONE = "insert into phones(countryCode, innerNumber) values(?, ?)";
    private final static String SQL_QUERY_GET_MAX_ID = "select max(id) maxid from phones";
    private Connection connection;

    public JdbcPhoneDao(){
        connection = ConnectionInstance.getInstance();
    }

    @Override
    public List<Phone> getAll() {
        return null;
    }

    @Override
    public void add(Phone phone) {
        try (Connection connection = this.connection){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_SAVE_PHONE);
            preparedStatement.setString(1, phone.getCountryCode());
            preparedStatement.setString(2, phone.getInnerNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getMaxId() {
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY_GET_MAX_ID);) {
            long id = resultSet.getLong("maxid");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
