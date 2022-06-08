package com.lapin.common.utility;


import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.ResponseBodyKeys;
import com.lapin.common.network.objimp.ResponseCommand;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ClientListener;
import com.lapin.network.obj.NetObj;

import java.io.Serializable;

public class Client_Network_IO implements Client_IO{
    NetworkConfigurator config;
    ClientListener listener;
    public Client_Network_IO(NetworkConfigurator config, ClientListener listener){
        this.config = config;
        this.listener = listener;
    }
    @Override
    public String handle(String command, String arg, Serializable argObj) {
        return Response2String(listener.handle(createRequest(command,arg,argObj)));
    }

    @Override
    public String handle(String command, String arg) {
        return handle(command,arg,null);
    }

    private RequestCommand createRequest(String command, String arg, Serializable argObj){
        return new RequestCommand(command,arg,argObj);
    }
    private String Response2String(NetObj response){
        return (String) response.getBody().get(ResponseBodyKeys.ANSWER);
    }
}
