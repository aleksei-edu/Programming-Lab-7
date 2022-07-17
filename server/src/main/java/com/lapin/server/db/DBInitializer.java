package com.lapin.server.db;

import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInitializer {
    private final DBConnector dbConnector;
    private final NetworkLogger Logger;


    public DBInitializer(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
        Logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        Logger.setLogOutput(new NetworkLogOutputConsole());
    }
    public int init(){
        try(
                Connection connection = dbConnector.connect();
                PreparedStatement createUsersTable = connection.prepareStatement(DBQuery.CREATE_USERS_TABLE.getQuery());
                PreparedStatement createLocationToTable = connection.prepareStatement(DBQuery.CREATE_LOCATION_TO_TABLE.getQuery());
                PreparedStatement createLocationFromTable = connection.prepareStatement(DBQuery.CREATE_LOCATION_FROM_TABLE.getQuery());
                PreparedStatement createCoordinatesTable = connection.prepareStatement(DBQuery.CREATE_COORDINATES_TABLE.getQuery());
                PreparedStatement createRouteTable = connection.prepareStatement(DBQuery.CREATE_ROUTE_TABLE.getQuery());
                ){
            createUsersTable.execute();
            createLocationToTable.execute();
            createLocationFromTable.execute();
            createCoordinatesTable.execute();
            createRouteTable.execute();
            Logger.info("DB initialization succeeded");
            return 1;
        } catch (SQLException e){
            Logger.error("Problems during DB initialization");
            return -1;
        }
    }
}
