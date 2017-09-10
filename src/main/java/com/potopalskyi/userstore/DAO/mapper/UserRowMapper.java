package com.potopalskyi.userstore.dao.mapper;

import com.potopalskyi.userstore.dao.ResultSetToPhoneListParser;
import com.potopalskyi.userstore.entity.User;
import com.potopalskyi.userstore.web.util.RequestToUserParser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("userId"));
        user.setFirstName(handleNull(resultSet, "firstName"));
        user.setLastName(handleNull(resultSet, "lastName"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
        user.setPhoneList(ResultSetToPhoneListParser.getPhoneList(resultSet));
        return user;
    }

    protected String handleNull(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        return (resultSet.wasNull()) ? "" : value;
    }
}
