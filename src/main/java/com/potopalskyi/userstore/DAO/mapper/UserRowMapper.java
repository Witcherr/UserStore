package com.potopalskyi.userstore.DAO.mapper;

import com.potopalskyi.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
        user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
        user.getPhone().setCountryCode(resultSet.getString("countryCode"));
        user.getPhone().setInnerNumber(resultSet.getString("innerNumber"));
        return user;
    }
}
