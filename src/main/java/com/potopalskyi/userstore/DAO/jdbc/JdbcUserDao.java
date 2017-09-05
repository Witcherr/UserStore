package com.potopalskyi.userstore.dao.jdbc;

import com.potopalskyi.userstore.dao.ConnectionInstance;
import com.potopalskyi.userstore.dao.UserDao;
import com.potopalskyi.userstore.dao.mapper.UserRowMapper;
import com.potopalskyi.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private final static String SQL_QUERY_GET_ALL_USERS = "select users.id, firstName, lastName, dateOfBirth, countryCode, innerNumber\n" +
            "from users\n" +
            "join phones\n" +
            "on users.phones_id = phones.id\n";
    private final static String SQL_QUERY_SAVE_USER = "insert into users(firstName, lastName, phones_id) values(?, ?, ?)";
    private Connection connection;

    public JdbcUserDao() throws SQLException {
        connection = ConnectionInstance.getInstance();
    }

    @Override
    public List<User> getAll() {
        List userList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY_GET_ALL_USERS);) {

            UserRowMapper userRowMapper = new UserRowMapper();
            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void add(User user) {
        try (Connection connection = this.connection){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_SAVE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getPhone().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
