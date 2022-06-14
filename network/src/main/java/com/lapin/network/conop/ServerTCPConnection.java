package com.lapin.network.conop;

import com.lapin.di.context.ApplicationContext;
import com.lapin.network.listener.Listenerable;
import com.lapin.network.listener.ServerListener;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;
import com.lapin.network.obj.RequestHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServerTCPConnection implements ConnectionType {
    private final Map<SocketChannel, ByteBuffer> channels = new HashMap<>();
    private ServerSocketChannel serv;
    private RequestHandler requestHandler;
    private Properties properties;
    private NetworkLogger netLogger;

    public ServerTCPConnection(RequestHandler requestHandler, File configPath) {
        netLogger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        netLogger.setLogOutput(new NetworkLogOutputConsole());
        this.requestHandler = requestHandler;
        properties = new Properties();
        try {
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            netLogger.error("Не удалось загрузить config");
        }
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
            int port = Integer.parseInt(properties.getProperty("port"));
            serv.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            netLogger.error("Port is not available!");
        }
        try {
            serv.configureBlocking(false);
        } catch (IOException e) {
            netLogger.error("Non-blocking mode not available!");
        }
        netLogger.info("Server is running");
        return new ServerListener(requestHandler,serv);
    }
}

