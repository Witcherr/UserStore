package com.potopalskyi.userstore.service;

import com.potopalskyi.userstore.dao.GenericDao;
import com.potopalskyi.userstore.entity.Phone;

public interface IPhoneService{

    long add(Phone phone);

    void delete(long id);

}
