package com.lapin.common.controllers;

import com.lapin.common.data.Route;
import com.lapin.common.data.User;

import java.util.Set;

public interface DBHandler {
    public long checkUser(User user);
    public long addUser(User user);
    public Set<Route> loadRoutes();
    public int addRoute(Route route, User user);
    public long updateRoute(Integer id, Route route, User user);
}
