package com.potopalskyi.userstore.dao;

import com.potopalskyi.userstore.entity.Phone;

public interface PhoneDao extends GenericDao<Phone> {

    long getMaxId();
}
