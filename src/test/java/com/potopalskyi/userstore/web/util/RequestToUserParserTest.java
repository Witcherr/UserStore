package com.potopalskyi.userstore.web.util;

import com.potopalskyi.userstore.entity.Phone;
import com.potopalskyi.userstore.entity.User;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestToUserParserTest {

    @Test
    public void testGetUserFromRequest() throws Exception {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        when(httpServletRequest.getParameter("firstName")).thenReturn("name");
        when(httpServletRequest.getParameter("lastName")).thenReturn("lastName");
        when(httpServletRequest.getParameter("dateOfBirth")).thenReturn(String.valueOf(Date.valueOf(LocalDate.of(2011, Month.JUNE, 11))));

        User user = RequestToUserParser.getUserFromRequest(httpServletRequest);

        assertEquals("name", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertTrue(user.getDateOfBirth().isEqual(LocalDate.of(2011, Month.JUNE, 11)));
    }

    @Test
    public void testGetPhoneFromRequest() throws Exception {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        when(httpServletRequest.getParameter("innerNumber_0")).thenReturn("");
        when(httpServletRequest.getParameter("innerNumber_0")).thenReturn("");
        when(httpServletRequest.getParameter("innerNumber_1")).thenReturn("number-12");
        when(httpServletRequest.getParameter("innerNumber_2")).thenReturn("number-34");
        when(httpServletRequest.getParameter("countryCode_1")).thenReturn("country-12");
        when(httpServletRequest.getParameter("countryCode_2")).thenReturn("country-34");

        List<Phone> phoneList = RequestToUserParser.getPhoneFromRequest(httpServletRequest, "countryCode_", "innerNumber_");

        assertEquals("country-34", phoneList.get(1).getCountryCode());
        assertEquals("number-12", phoneList.get(0).getInnerNumber());
    }
}