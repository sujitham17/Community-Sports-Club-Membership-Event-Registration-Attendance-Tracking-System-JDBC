package com.club.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() throws Exception {

        // Load Oracle JDBC Driver (CLASS NAME)
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Create Connection (URL)
        return DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE",
            "club_user",
            "club_pwd"
        );
    }
}
