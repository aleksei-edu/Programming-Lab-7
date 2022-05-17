package com.lapin.network.conop;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.log.NetworkLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

public class ClientTCPConnection implements ConnectionType {
    private SocketChannel socketChannel;
    private final NetworkConfigurator config;
    private SocketAddress addr;
    private final NetworkLogger netLogger;
    public ClientTCPConnection(NetworkConfigurator config){
        this.config = config;
        this.netLogger = config.getNetLogger();
    }
    @Override
    public boolean openSocket() {
        try {
            socketChannel = SocketChannel.open();
        } catch (IOException e) {
            netLogger.error("Failed to open socket!");
            return false;
        }
        try {
            addr = new InetSocketAddress(config.getHost(), config.getPort());
        }
        catch (UnknownHostException e){
            netLogger.error("Failed! Unknown host!");
            return false;
        }
        return true;
    }

    @Override
    public boolean connect() {
        int reconnectionAttempts = 1;
        boolean processingStatus = true;
        while (processingStatus) {
            if(reconnectionAttempts >= 2) netLogger.info("Reconnecting to the server...");
            else netLogger.info("Connecting to the server...");
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(addr);
            } catch (IOException e) {
                netLogger.error("Server connection error!");
                reconnectionAttempts++;
                if(reconnectionAttempts > config.getMaxReconnectionAttemps()){
                    netLogger.error("Connection timed out!");
                    return false;
                }
                try {
                    netLogger.info((reconnectionAttempts) +" reconnection attempt will be made after "
                            + (config.getTimeout()/1000) + " seconds.");
                    Thread.sleep(config.getTimeout());
                } catch (InterruptedException ex) {
                    netLogger.error("Client sleep error!");
                }
                continue;
            }
            processingStatus = false;
            netLogger.info("Client connected!");
        }
        return true;
    }

    @Override
    public boolean run() {
        return false;
    }
}
