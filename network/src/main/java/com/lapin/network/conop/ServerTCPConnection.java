package com.lapin.network.conop;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.log.NetworkLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Map;

public class ServerTCPConnection implements ConnectionType {
    private final Map<SocketChannel, ByteBuffer> channels = new HashMap<>();
    private ServerSocketChannel serv;
    private NetworkConfigurator config;
    private NetworkLogger netLogger;
    private Selector sel;
    private SelectionKey key;

    public ServerTCPConnection(NetworkConfigurator config) {
        this.config = config;
        this.netLogger = config.getNetLogger();
    }

    @Override
    public boolean openSocket() {
        try {
            serv = ServerSocketChannel.open();
        } catch (IOException e) {
            netLogger.error("Failed to open socket!");
            return false;
        }
        return true;
    }

    @Override
    public boolean connect() {
        try {
            serv.bind(new InetSocketAddress(8000));
        } catch (IOException e) {
            netLogger.error("Port is not available!");
            return false;
        }
        try {
            serv.configureBlocking(false);
        } catch (IOException e) {
            netLogger.error("Non-blocking mode not available!");
            return false;
        }
        netLogger.info("Server is running");
        return true;
    }
    private void close(){
        try {
            sel.close();
            serv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean run() {
        config.getListener(serv).startUp();
        return false;
    }

}

