package com.buku.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public final class Database {

//    static private final String DRIVER = "com.mysql.jdbc.driver";

    static private String URL = "jdbc:mysql://localhost:3306/";

    static private final String user = "root";

    static private final String password = "";

    private static final Connection connection;

    static {
        try {
            String env = System.getenv("env");

            if (System.getenv("env") == null) {
                env = "";
            }


            if (env.equals("test")) {
                URL += "buku_test";
            } else {
                URL += "buku";
                System.err.println("[" + LocalDate.now() + "] you running on product, please be carefull");
            }

            System.out.println();

            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
