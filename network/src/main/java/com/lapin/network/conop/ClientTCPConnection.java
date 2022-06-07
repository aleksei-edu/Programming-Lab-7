package com.lapin.network.conop;

import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ClientListener;
import com.lapin.network.listener.Listenerable;
import com.lapin.network.log.NetworkLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

public class ClientTCPConnection implements ConnectionType {
    private SocketChannel socketChannel;
    private SocketAddress addr;
    private final NetworkLogger netLogger;
    private NetworkConfigurator config;

    public ClientTCPConnection(NetworkConfigurator config) {
        this.config = config;
        this.netLogger = config.getNetLogger();
    }


    @Override
    public void openSocket() {
        try {
            socketChannel = SocketChannel.open();
        } catch (IOException e) {
            netLogger.error("Failed to open socket!");
        }
        try {
            addr = new InetSocketAddress(config.getHost(), config.getPort());
        } catch (UnknownHostException e) {
            netLogger.error("Failed! Unknown host!");
        }
    }

    @Override
    public Listenerable connect() {
        int reconnectionAttempts = 1;
        boolean processingStatus = true;
        while (processingStatus) {
            if (reconnectionAttempts >= 2) netLogger.info("Reconnecting to the server...");
            else netLogger.info("Connecting to the server...");
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(addr);
            } catch (IOException e) {
                netLogger.error("Server connection error!");
                reconnectionAttempts++;
                if (reconnectionAttempts > config.getMaxReconnectionAttemps()) {
                    netLogger.error("Connection timed out!");
                }
                try {
                    netLogger.info((reconnectionAttempts) + " reconnection attempt will be made after "
                            + (config.getTimeout() / 1000) + " seconds.");
                    Thread.sleep(config.getTimeout());
                } catch (InterruptedException ex) {
                    netLogger.error("Client sleep error!");
                }
                continue;
            }
            processingStatus = false;
            netLogger.info("Client connected!");
        }
        return new ClientListener(config,socketChannel);
    }

}
