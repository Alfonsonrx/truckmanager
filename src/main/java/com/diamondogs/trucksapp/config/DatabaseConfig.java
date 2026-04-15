package com.diamondogs.trucksapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/db_camiones"
                + "?useSSL=false"
                +"&serverTimezone=UTC"
                +"&allowPublicKeyRetrieval=true");
        config.setUsername("root");
        config.setPassword("");

//        config.setMaximumPoolSize(10);           // Max 10 simultaneous connections
        config.setMinimumIdle(2);
        config.setIdleTimeout(300000);           // 5 minutes
        config.setConnectionTimeout(20000);      // 20 seconds max wait
        config.setValidationTimeout(3000);

//        config.setConnectionTestQuery("SELECT 1");

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public static void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
