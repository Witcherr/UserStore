package com.potopalskyi.userstore.dao.mapper;

import com.potopalskyi.userstore.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void testMapRow() throws Exception {
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("userId")).thenReturn(1L);
        when(resultSet.getString("firstName")).thenReturn("Test First Name");
        when(resultSet.getString("lastName")).thenReturn("Test Last Name");
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf(LocalDate.of(2011, Month.JUNE, 11)));
        when(resultSet.getString("phoneIdList")).thenReturn("4, 5");
        when(resultSet.getString("countryCodeList")).thenReturn("12, 345345");
        when(resultSet.getString("innerNumberList")).thenReturn("1--2, 34-5-345");

        User actualUser = userRowMapper.mapRow(resultSet);

        assertEquals(1, actualUser.getId());
        assertEquals("Test First Name", actualUser.getFirstName());
        assertEquals("Test Last Name", actualUser.getLastName());
        assertTrue(actualUser.getDateOfBirth().isEqual(LocalDate.of(2011, Month.JUNE, 11)));
        assertEquals(5L, actualUser.getPhoneList().get(1).getId());
        assertEquals("12", actualUser.getPhoneList().get(0).getCountryCode());
        assertEquals("34-5-345", actualUser.getPhoneList().get(1).getInnerNumber());
    }

    @Test
    public void testHandleNull() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("test")).thenReturn("Testvalue");
        when(resultSet.wasNull()).thenReturn(true);

        String actualValueNull =  userRowMapper.handleNull(resultSet, "test");

        assertEquals("", actualValueNull);

        when(resultSet.wasNull()).thenReturn(false);

        String actualValue2 =  userRowMapper.handleNull(resultSet, "test");

        assertEquals("Testvalue", actualValue2);
    }


}