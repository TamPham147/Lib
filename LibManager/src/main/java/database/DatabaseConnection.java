package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:sqlserver://NGUYENTAM:1433;databaseName=LibraryDB;encrypt=false;trustServerCertificate=true;";
        String username = "sa";
        String password = "123456";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connect Success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
