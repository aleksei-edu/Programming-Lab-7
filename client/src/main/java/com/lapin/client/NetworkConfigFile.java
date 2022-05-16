package com.lapin.client;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.conop.ConnectionType;
import com.lapin.network.log.NetworkLogger;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;


public class NetworkConfigFile implements NetworkConfigurator {


    @Override
    public ConnectionType getConnectionType() {
        return null;
    }

    @Override
    public NetworkLogger getNetLogger() {
        return null;
    }

    public Integer getPort() {
        return 8888;
    }


    public Integer getTimeout() {
        return 1*60*100;
    }

    public Integer getMaxReconnectionAttemps() {
        return 5;
    }
}
