package com.lapin.network;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.conop.ConnectionType;
import com.lapin.network.listener.Listenerable;
import com.lapin.network.log.NetworkLogger;
import com.lapin.network.obj.NetObj;

import java.io.*;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

public class TCPConnection {
    private AbstractSelectableChannel socket;
    private final NetworkConfigurator config;
    private ConnectionType connection;
    public TCPConnection(NetworkConfigurator config){
        this.config = config;
        connection = config.getConnectionType();
    }
    public Listenerable start(){
        connection.openSocket();
        return connection.connect();
    }

}
