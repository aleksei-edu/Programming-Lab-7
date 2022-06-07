package com.lapin.server;

import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.RequestHeaderKey;
import com.lapin.common.network.objimp.ResponseCommand;
import com.lapin.common.utility.OutManager;
import com.lapin.common.utility.Pair;
import com.lapin.network.StatusCodes;
import com.lapin.network.obj.NetObj;
import com.lapin.network.obj.RequestHandler;
import com.lapin.common.utility.CommandManager;

public class ServerRequestHandler implements RequestHandler {
    @Override
    public NetObj handle(NetObj netObj) {
        if(netObj != null){
            RequestCommand rc = (RequestCommand) netObj;
            CommandManager.execute(rc.getCmd(), rc.getArg(), rc.argObj());
            Pair response = OutManager.pop();
            return new ResponseCommand((StatusCodes) response.getFirst(),(String) response.getSecond());
        }
        return null;
    }
}
