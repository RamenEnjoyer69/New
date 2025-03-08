package com.ramenenjoyer_69.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    public static void driver(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/storeManagement";
        String username = "postgres";
        String password = "chompou";
        try(Connection conn = DriverManager.getConnection(url, username, password);) {

            // Create connection
            System.out.println(conn.isValid(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
