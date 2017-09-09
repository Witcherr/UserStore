package com.potopalskyi.userstore.dao.jdbc;

import com.potopalskyi.userstore.dao.ConnectionInstance;
import com.potopalskyi.userstore.dao.UserDao;
import com.potopalskyi.userstore.dao.mapper.UserRowMapper;
import com.potopalskyi.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private final static String SQL_QUERY_GET_ALL_USERS = "select users.id userId, firstName, lastName, dateOfBirth, \n" +
            "group_concat(phones.id order by phones.id separator ', ') as phoneIdList, \n" +
            "group_concat(countryCode order by phones.id separator ', ') as countryCodeList, \n" +
            "group_concat(innerNumber order by phones.id separator ', ') as innerNumberList\n" +
            "from users\n" +
            "left join phones\n" +
            "on users.id = phones.userId\n" +
            "group by 1, 2, 3, 4";
    private final static String SQL_QUERY_SAVE_USER = "insert into users(firstName, lastName, dateOfBirth) values(?, ?, ?)";
    private final static String SQL_QUERY_DELETE_USER = "delete from users where id = ?";
    private final static String SQL_QUERY_GET_USERS_BY_INNER_CODE = "select users.id userId, firstName, lastName, dateOfBirth, \n" +
            "group_concat(phones.id order by phones.id separator ', ') as phoneIdList, \n" +
            "group_concat(countryCode order by phones.id separator ', ') as countryCodeList, \n" +
            "group_concat(innerNumber order by phones.id separator ', ') as innerNumberList\n" +
            "from users\n" +
            "join phones\n" +
            "on users.id = phones.userId\n" +
            "where innerNumber LIKE  CONCAT('%', ? ,'%')\n" +
            "group by 1, 2, 3, 4";
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
    public long add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long userId = resultSet.getInt(1);
                return userId;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsersByInnerCode(String innerCode) {
        List userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USERS_BY_INNER_CODE);
            preparedStatement.setString(1, innerCode);
            UserRowMapper userRowMapper = new UserRowMapper();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
