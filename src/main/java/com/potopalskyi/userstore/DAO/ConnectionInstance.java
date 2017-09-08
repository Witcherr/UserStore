package com.potopalskyi.userstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionInstance {
    private static Connection connectionInstance;

    private ConnectionInstance() {
    }

    public static Connection getInstance() {
        if (connectionInstance == null) {
            try {
                connectionInstance = DriverManager.getConnection("jdbc:mysql://localhost:3306/paymentsdb", "root", "witcher");
                connectionInstance.setAutoCommit(false);
                return connectionInstance;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionInstance;
    }
}


