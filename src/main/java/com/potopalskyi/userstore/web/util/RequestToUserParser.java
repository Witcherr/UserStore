package com.potopalskyi.userstore.web.util;

import com.potopalskyi.userstore.entity.Phone;
import com.potopalskyi.userstore.entity.User;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestToUserParser {
    public static final int MAX_PHONES_AMOUNT = 3;

    public static User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        try {
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
            user.setPhoneList(getPhoneFromRequest(req, "innerNumber_", "countryCode_"));
            //user.getPhoneList().setCountryCode(req.getParameter("countryCode"));
            //user.getPhoneList().setInnerNumber(req.getParameter("innerNumber"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private static List<Phone> getPhoneFromRequest(HttpServletRequest req, String countryCodeParameter, String innerNumberParameter) {
        String countryCode;
        String innerNumber;
        List<Phone> phoneList = new ArrayList<>();
        for (int i = 0; i < MAX_PHONES_AMOUNT; i++) {
            countryCode = req.getParameter(countryCodeParameter.concat(String.valueOf(i)));
            innerNumber = req.getParameter(innerNumberParameter.concat(String.valueOf(i)));

            if (!"".equals(countryCode) && !"".equals(innerNumber)) {
                Phone phone = new Phone();
                phone.setCountryCode(countryCode);
                phone.setInnerNumber(innerNumber);
                phoneList.add(phone);
            }
        }
        return phoneList;
    }
}
