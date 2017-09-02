package com.potopalskyi.userstore.DAO.mapper;

import com.potopalskyi.userstore.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
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

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("firstName")).thenReturn("Test First Name");
        when(resultSet.getString("lastName")).thenReturn("Test Last Name");
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf(LocalDate.of(2011, Month.JUNE, 11)));
        when(resultSet.getString("countryCode")).thenReturn("Test country code");
        when(resultSet.getString("innerNumber")).thenReturn("Test inner number");

        User actualUser = userRowMapper.mapRow(resultSet);

        assertEquals(1, actualUser.getId());
        assertEquals("Test First Name", actualUser.getFirstName());
        assertEquals("Test Last Name", actualUser.getLastName());
        assertTrue(actualUser.getDateOfBirth().isEqual(LocalDate.of(2011, Month.JUNE, 11)));
        assertEquals("Test country code", actualUser.getPhone().getCountryCode());
        assertEquals("Test inner number", actualUser.getPhone().getInnerNumber());
    }


}