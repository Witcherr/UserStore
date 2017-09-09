package com.potopalskyi.userstore.dao;

import com.potopalskyi.userstore.entity.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToPhoneListParser {

    public static List<Phone> getPhoneList(ResultSet resultSet) throws SQLException {
        List<Phone> phoneList = new ArrayList<>();
        String[] phoneIdArray = handleNull(resultSet, "phoneIdList").split(",");
        String[] countryCodeArray = handleNull(resultSet, "countryCodeList").split(",");
        String[] innerNumberArray = handleNull(resultSet, "innerNumberList").split(",");
        if(validatePhoneList(phoneIdArray[0])){
            for (int i = 0; i < phoneIdArray.length; i++) {
                Phone phone = new Phone();
                phone.setId(Long.parseLong(phoneIdArray[i].trim()));
                phone.setCountryCode(countryCodeArray[i].trim());
                phone.setInnerNumber(innerNumberArray[i].trim());
                phoneList.add(phone);
            }
        }
        return phoneList;
    }

    private static String handleNull(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        return (resultSet.wasNull()) ? "" : value;
    }

    private static boolean validatePhoneList(String phoneId){
        return !"".equals(phoneId);
    }
}
