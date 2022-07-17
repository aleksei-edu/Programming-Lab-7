package com.lapin.common.controllers;

import com.lapin.common.data.User;

public interface DBHandler {
    public long checkUser(User user);
    public long addUser(User user);
}
