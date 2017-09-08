package com.potopalskyi.userstore.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private Connection connectionInstance;

    public TransactionManager() {
        connectionInstance = ConnectionInstance.getInstance();
    }

    public void commit() {
        try {
            connectionInstance.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connectionInstance.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
