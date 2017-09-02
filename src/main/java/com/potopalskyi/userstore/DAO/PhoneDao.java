package com.potopalskyi.userstore.DAO;

import com.potopalskyi.userstore.entity.Phone;

public interface PhoneDao extends GenericDao<Phone> {

    long getMaxId();
}
