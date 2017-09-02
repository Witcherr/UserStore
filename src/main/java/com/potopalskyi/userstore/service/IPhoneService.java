package com.potopalskyi.userstore.service;

import com.potopalskyi.userstore.entity.Phone;

public interface IPhoneService {

    void add(Phone phone);

    long getMaxId();
}
