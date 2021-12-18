package com.tie.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HealthCheck {

    public static boolean getRemoteConnection(String jdbcUrl, String user, String password) throws SQLException {

        Connection con = DriverManager.getConnection(jdbcUrl + "?user=" + user + "&password=" + password);
        return con != null;
    }
}
