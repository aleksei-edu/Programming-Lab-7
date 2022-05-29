package com.lapin.common.utility;

import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.obj.ClientRequestHandler;
import com.lapin.network.obj.NetObj;

public class ClientReqHandler implements ClientRequestHandler {
    @Override
    public NetObj handle(String str, NetworkConfigurator config) {
        String[] userCommand = (str.toLowerCase().trim() + " ").split(" ", 2);
        RequestCommand requestCommand = CommandManager.processCommand(userCommand[0], userCommand[1].trim(),config);
        if (requestCommand == null){
            OutManager.println();
        }
        return requestCommand;
    }
}
