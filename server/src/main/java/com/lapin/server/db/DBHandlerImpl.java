package com.lapin.server.db;

import com.lapin.common.data.*;
import com.lapin.common.controllers.DBHandler;
import com.lapin.common.encrypt.Encryptor;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBHandlerImpl implements DBHandler {
    private final DBConnector dbConnector;
    private final Encryptor encryptor;
    private final NetworkLogger Logger;

    public DBHandlerImpl(DBConnector dbConnector, Encryptor encryptor){
        this.dbConnector = dbConnector;
        this.encryptor = encryptor;
        Logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        Logger.setLogOutput(new NetworkLogOutputConsole());
    }

    public synchronized long addUser(User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement addUserToTable = connection.prepareStatement(DBQuery.INSERT_USER.getQuery()))
        {
            addUserToTable.setString(1, user.getLogin());
            addUserToTable.setString(2, encryptor.encrypt(user.getPassword()));

            ResultSet resultSet = addUserToTable.executeQuery();
            resultSet.next();
            long userId = resultSet.getLong("user_id");
            user.setId(userId);
            Logger.info("New user added to database with id - " + userId);
            return userId;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            return -1;
        }
    }

    public synchronized long checkUser(User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement checkUser = connection.prepareStatement(DBQuery.SELECT_USER_BY_LOGIN_AND_PASSWORD.getQuery());
        ) {
            checkUser.setString(1, user.getLogin());
            checkUser.setString(2, encryptor.encrypt(user.getPassword()));

            ResultSet resultSet = checkUser.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                Logger.info("User with id - " + userId + " found");
                return userId;
            }
            return 0;
        } catch (SQLException e) {
            Logger.error("User not found.");
            return -1;
        }
    }

    public synchronized Set<Route> loadRoutes(){
        Set<Route> routes = Collections.synchronizedSet(new LinkedHashSet<>());
        try(
                Connection connection = dbConnector.connect();
                PreparedStatement selectAllRoutes = connection.prepareStatement(DBQuery.SELECT_ALL_ROUTES.getQuery());
                ){
            ResultSet resultSet = selectAllRoutes.executeQuery();
            while (resultSet.next()){
                Route route = new Route(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Coordinates(
                                resultSet.getDouble("coord_x"),
                                resultSet.getDouble("coord_y")
                        ),
                        resultSet.getDate("creation_date").toLocalDate(),
                        new LocationFrom(
                                resultSet.getInt("loc_from_x"),
                                resultSet.getFloat("loc_from_y"),
                                resultSet.getDouble("loc_from_z")
                        ),
                        new LocationTo(
                                resultSet.getFloat("loc_to_x"),
                                resultSet.getLong("loc_to_y"),
                                resultSet.getString("loc_to_name")
                        ),
                        resultSet.getLong("distance")
                        );
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return routes;
    }
}
