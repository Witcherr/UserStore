package com.potopalskyi.userstore.web.util;

import com.potopalskyi.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestToUserParser {

    public static User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        try {
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
            user.getPhone().setCountryCode(req.getParameter("countryCode"));
            user.getPhone().setInnerNumber(req.getParameter("innerNumber"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
