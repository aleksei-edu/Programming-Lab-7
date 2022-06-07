package com.lapin.network.conop;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.Listenerable;
import com.lapin.network.listener.ServerListener;
import com.lapin.network.log.NetworkLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.util.HashMap;
import java.util.Map;

public class ServerTCPConnection implements ConnectionType {
    private final Map<SocketChannel, ByteBuffer> channels = new HashMap<>();
    private ServerSocketChannel serv;
    private NetworkConfigurator config;
    private NetworkLogger netLogger;

    public ServerTCPConnection(NetworkConfigurator config) {
        this.config = config;
        this.netLogger = config.getNetLogger();
    }

    @Override
    public void openSocket() {
        try {
            serv = ServerSocketChannel.open();
        } catch (IOException e) {
            netLogger.error("Failed to open socket!");
        }
    }

    @Override
    public Listenerable connect() {
        try {
            serv.bind(new InetSocketAddress(8000));
        } catch (IOException e) {
            netLogger.error("Port is not available!");
        }
        try {
            serv.configureBlocking(false);
        } catch (IOException e) {
            netLogger.error("Non-blocking mode not available!");
        }
        netLogger.info("Server is running");
        return new ServerListener(config,serv);
    }
}

