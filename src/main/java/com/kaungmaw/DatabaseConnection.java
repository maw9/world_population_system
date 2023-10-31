/*
package com.kaungmaw;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static String username = "root";
    private static String password = "";
    private static boolean isCreated = false;
    private static Connection conn;

    public static Connection getInstance() {
        if (!isCreated) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Checking Driver class existence by Driver file path
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/world_population",
                        username,
                        password
                );
                System.out.println("Database is connected");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                conn = null;
            }
            isCreated = true;
        }
        return conn;
    }

    private DatabaseConnection() {}

}
*/
