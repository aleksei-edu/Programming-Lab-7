package com.lapin.common.controllers;

import com.lapin.common.client.Client;
import com.lapin.common.data.Route;
import com.lapin.common.utility.Client_IO;
import com.lapin.common.utility.Pair;
import com.lapin.network.StatusCodes;

import java.io.Serializable;

public interface CommandManager {
    public Pair<StatusCodes,Object> handle(String userCommand, String argument, Serializable argObj);
    public void execute(String userCommand, String argument, Serializable argObj);
    public void setClientIO(Client_IO clientIO);
    public void setClient(Client client);
    public Client getClient();
}
