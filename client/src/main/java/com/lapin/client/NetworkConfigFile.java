package com.lapin.client;

import com.lapin.network.ClientType;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.conop.ClientTCPConnection;
import com.lapin.network.conop.ConnectionType;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;


public class NetworkConfigFile implements NetworkConfigurator {


    @Override
    public ConnectionType getConnectionType() {
        return new ClientTCPConnection(this);
    }

    @Override
    public NetworkLogger getNetLogger() {
        return new NetworkLogger(new NetworkLogOutputConsole());
    }

    public Integer getPort() {
        return 8000;
    }

    @Override
    public ClientType getClientType() {
        return ClientType.USER;
    }

    public Integer getTimeout() {
        return 1*60*100;
    }

    public Integer getMaxReconnectionAttemps() {
        return 5;
    }
}
