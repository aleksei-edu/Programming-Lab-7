package com.lapin.server;

import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.ResponceCommand;
import com.lapin.common.utility.OutManager;
import com.lapin.network.obj.NetObj;
import com.lapin.network.obj.RequestHandler;
import com.lapin.network.obj.Serializer;
import com.lapin.server.utility.CommandManager;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

public class ServerRequestHandler implements RequestHandler {
    @Override
    public NetObj handle(NetObj netObj) {
        if(netObj != null && netObj.getHeader().equals("request")){
            RequestCommand rc = (RequestCommand) netObj;
            CommandManager.execute(rc.getCmd(), rc.getArg());
            return new ResponceCommand(OutManager.pop());
        }
        return null;
    }
}
