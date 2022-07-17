package com.lapin.common.controllers;

import com.lapin.common.data.Route;

import java.io.Serializable;

public interface CommandManager {
    public void execute(String userCommand, String argument, Route argObj);
    public void execute(String userCommand, String argument, Serializable argObj);

}
