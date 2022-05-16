package com.lapin.network;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.conop.ConnectionType;
import com.lapin.network.log.NetworkLogger;

import java.io.*;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class TCPConnection {
    private final ConnectionType connectionType;

    public TCPConnection(NetworkConfigurator config) {
        this.connectionType = config.getConnectionType();
    }
    public void run(){
        connectionType.openSocket();
        connectionType.connect();
        connectionType.run();
    }
}
