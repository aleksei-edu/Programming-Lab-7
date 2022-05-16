package com.lapin.network.config;

import com.lapin.network.conop.ConnectionType;
import com.lapin.network.listener.ServerListener;
import com.lapin.network.log.NetworkLogger;
import com.lapin.network.obj.RequestHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;

public interface NetworkConfigurator {
    //Параметры для клиента и сервера
    public ConnectionType getConnectionType();
    public NetworkLogger getNetLogger();
    public Integer getPort();
    public default InetAddress getHost() throws UnknownHostException{
        InetAddress host = null;
        host = InetAddress.getLocalHost();
        if (host == null){
            throw new UnknownHostException();
        }
        return host;
    };
    //Параметры для клиента.
    public default Integer getTimeout(){
        return null;
    };
    public default Integer getMaxReconnectionAttemps(){
        return null;
    };

    //параметры для сервера
    public default ServerListener getListener(ServerSocketChannel serverSocketChannel){
        return null;
    };
    public default RequestHandler getRequestHandler(){
        return null;
    };
}
