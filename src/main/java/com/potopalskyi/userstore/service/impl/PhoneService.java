package com.potopalskyi.userstore.service.impl;

import com.potopalskyi.userstore.dao.PhoneDao;
import com.potopalskyi.userstore.entity.Phone;
import com.potopalskyi.userstore.service.IPhoneService;

public class PhoneService implements IPhoneService {
    private PhoneDao phoneDao;

    @Override
    public void add(Phone phone) {
        phoneDao.add(phone);
    }

    @Override
    public long getMaxId() {
        return phoneDao.getMaxId();
    }

    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }
}
