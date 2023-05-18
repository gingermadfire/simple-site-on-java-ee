package com.gingermadfire.javaeesimplesite.config;

import com.gingermadfire.javaeesimplesite.exception.DatabaseConnectionException;
import jakarta.ejb.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class DatabaseConnectionConfig {

    public static Connection getConnection() {
        final String url = "jdbc:postgresql://localhost:5432/internet_store";
        final String username = "postgres";
        final String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Не удалось подключиться к базе данных", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
