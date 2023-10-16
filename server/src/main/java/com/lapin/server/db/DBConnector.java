package com.lapin.server.db;

import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;
import com.lapin.server.App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static String JDBC_DRIVER;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_URL;
    private final Properties properties = new Properties();
    private final NetworkLogger logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
    {
        logger.setLogOutput(new NetworkLogOutputConsole());
        try {
            properties.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
           logger.error("Config" +
                   " file is not found.");
        }
        JDBC_DRIVER = properties.getProperty("jdbc_driver");
        String DB_HOST = properties.getProperty("db_host");
        String DB_NAME = properties.getProperty("db_name");
        DB_USER = properties.getProperty("db_user");
        String DB_PORT = properties.getProperty("port");
        DB_PASSWORD = properties.getProperty("db_password");
        DB_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    }
    public Connection connect() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found");
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
