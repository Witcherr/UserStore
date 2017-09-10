package com.potopalskyi.userstore.dao;

import com.potopalskyi.userstore.dao.mapper.UserRowMapper;
import com.potopalskyi.userstore.entity.Phone;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultSetToPhoneListParserTest {

    @Test
    public void testGetPhoneList() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString("phoneIdList")).thenReturn("1, 2");
        when(resultSet.getString("countryCodeList")).thenReturn("123, 234-34");
        when(resultSet.getString("innerNumberList")).thenReturn("2341, gj345");
        List<Phone> phoneList = ResultSetToPhoneListParser.getPhoneList(resultSet);

        assertEquals(2L, phoneList.get(1).getId());
        assertEquals("234-34", phoneList.get(1).getCountryCode());
        assertEquals("2341", phoneList.get(0).getInnerNumber());
    }

    @Test
    public void testHandleNull() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("test")).thenReturn("Testvalue");
        when(resultSet.wasNull()).thenReturn(true);

        String actualValueNull =  ResultSetToPhoneListParser.handleNull(resultSet, "test");

        assertEquals("", actualValueNull);

        when(resultSet.wasNull()).thenReturn(false);

        String actualValue2 =  ResultSetToPhoneListParser.handleNull(resultSet, "test");

        assertEquals("Testvalue", actualValue2);
    }

    @Test
    public void testValidatePhoneList() throws Exception {
        String emptyString = "";
        String notEmptyString = "test";
        assertFalse(ResultSetToPhoneListParser.validatePhoneList(emptyString));
        assertTrue(ResultSetToPhoneListParser.validatePhoneList(notEmptyString));
     }
}