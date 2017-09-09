package com.potopalskyi.userstore.dao.mapper;

import com.potopalskyi.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("userId"));
        user.setFirstName(handleNull(resultSet, "firstName"));
        user.setLastName(handleNull(resultSet, "lastName"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
        user.getPhone().setId(resultSet.getLong("phoneId"));
        user.getPhone().setCountryCode(handleNull(resultSet, "countryCode"));
        user.getPhone().setInnerNumber(handleNull(resultSet, "innerNumber"));
        return user;
    }

    private String handleNull(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        return (resultSet.wasNull()) ? "" : value;
    }
}
