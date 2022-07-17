package com.lapin.server.db;

import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;
import org.postgresql.Driver;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String HOST = "localhost";
    private static final String DB_NAME = "studs";
    private static final String USER = "aleksejlapin";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:postgresql://" + HOST + ":5432/" + DB_NAME;

    public Connection connect() throws SQLException {
        NetworkLogger logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        logger.setLogOutput(new NetworkLogOutputConsole());
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found");
        }
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
