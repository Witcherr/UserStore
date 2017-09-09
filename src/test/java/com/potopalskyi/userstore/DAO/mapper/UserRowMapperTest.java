package com.potopalskyi.userstore.dao.mapper;

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

        when(resultSet.getLong("userId")).thenReturn(1L);
        when(resultSet.getString("firstName")).thenReturn("Test First Name");
        when(resultSet.getString("lastName")).thenReturn("Test Last Name");
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf(LocalDate.of(2011, Month.JUNE, 11)));
        when(resultSet.getString("phoneIdList")).thenReturn("4, 5");
        when(resultSet.getString("countryCodeList")).thenReturn("12, 345345");
        when(resultSet.getString("innerNumberList")).thenReturn("1--2, 34-5-345");
        //when(resultSet.getLong("phoneId")).thenReturn(10L);
        //when(resultSet.getString("countryCode")).thenReturn("Test country code");
        //when(resultSet.getString("innerNumber")).thenReturn("Test inner number");

        User actualUser = userRowMapper.mapRow(resultSet);
        System.out.println(actualUser.getPhoneList().get(0).getInnerNumber());
        assertEquals(1, actualUser.getId());
        assertEquals("Test First Name", actualUser.getFirstName());
        assertEquals("Test Last Name", actualUser.getLastName());
        assertTrue(actualUser.getDateOfBirth().isEqual(LocalDate.of(2011, Month.JUNE, 11)));
        assertEquals(5L, actualUser.getPhoneList().get(1).getId());
        assertEquals("12", actualUser.getPhoneList().get(0).getCountryCode());
        assertEquals("34-5-345", actualUser.getPhoneList().get(1).getInnerNumber());
        //assertEquals(10, actualUser.getPhoneList().getId());
        //assertEquals("Test country code", actualUser.getPhoneList().getCountryCode());
        //assertEquals("Test inner number", actualUser.getPhoneList().getInnerNumber());
    }


}