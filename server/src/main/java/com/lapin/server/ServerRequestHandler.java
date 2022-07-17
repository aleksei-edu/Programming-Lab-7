package com.lapin.server;

import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.ResponseCommand;
import com.lapin.common.utility.OutManager;
import com.lapin.common.utility.Pair;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.StatusCodes;
import com.lapin.network.obj.NetObj;
import com.lapin.network.obj.RequestHandler;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.server.BeforeRequestExecute.BeforeRequestExecute;

import java.lang.reflect.InvocationTargetException;

public class ServerRequestHandler implements RequestHandler {
    private StatusCodes statusCodes;
    @Override
    public NetObj handle(NetObj netObj) {
        if(netObj != null){
            RequestCommand rc = (RequestCommand) netObj;
            execute(rc);
            Pair response = OutManager.pop();
            statusCodes = (StatusCodes) response.getFirst();
            return new ResponseCommand(statusCodes,(String) response.getSecond());
        }
        return null;
    }
    private void execute(RequestCommand rc){
        if(callBeforeRequestExecute(rc)){
            CommandManagerImpl.execute(rc.getCmd(), rc.getArg(), rc.argObj());
        }
    }
    private boolean callBeforeRequestExecute(RequestCommand rc) {
        var processors = ApplicationContext
                .getInstance()
                .getBeanFactory()
                .getBeanConfigurator()
                .getScanner()
                .getSubTypesOf(BeforeRequestExecute.class);
        for(var processor : processors){
            try {
                if(!processor.getDeclaredConstructor().newInstance().process(rc)){
                    return false;
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public StatusCodes getStatusCode() {
        return statusCodes;
    }
}
