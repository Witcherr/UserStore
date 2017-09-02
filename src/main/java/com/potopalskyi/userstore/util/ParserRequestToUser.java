package com.potopalskyi.userstore.util;

import com.potopalskyi.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ParserRequestToUser {

    public static User getUserFromRequest(HttpServletRequest req){
        User user = new User();
        try{
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.getPhone().setCountryCode(req.getParameter("countryCode"));
            user.getPhone().setInnerNumber(req.getParameter("innerNumber"));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }
}
