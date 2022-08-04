package com.lapin.common.controllers;

import com.lapin.common.data.Route;
import com.lapin.common.data.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public interface CollectionManager {

    public Set<Route> getRouteCollection();

    public ArrayList<String[]> getStringRouteCollection();

    public void saveTimeCollection();

    public void add(Route route, User user);

    public boolean update(Integer id, Route newRoute, User user);

    public LocalDate getLastInitTime();

    public LocalDate getSaveTimeCollection();

    public void loadCollection();

    public long deleteRouteByID(int routeID, long authorID);

    public long clear(long authorId);
}
