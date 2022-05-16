package com.lapin.server.config;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.conop.ConnectionType;
import com.lapin.network.conop.ServerTCPConnection;
import com.lapin.network.listener.ServerListener;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;
import com.lapin.network.obj.RequestHandler;
import com.lapin.server.ServerRequestHandler;

import java.nio.channels.ServerSocketChannel;

public class NetworkConfigFile implements NetworkConfigurator {
    @Override
    public Integer getPort() {
        return 8888;
    }


    @Override
    public ConnectionType getConnectionType() {
        return new ServerTCPConnection(this);
    }

    @Override
    public ServerListener getListener(ServerSocketChannel serverSocketChannel) {
        return new ServerListener(this,serverSocketChannel);
    }

    @Override
    public NetworkLogger getNetLogger() {
        return new NetworkLogger(new NetworkLogOutputConsole());
    }

    @Override
    public RequestHandler getRequestHandler(){
        return new ServerRequestHandler();
    }
}
