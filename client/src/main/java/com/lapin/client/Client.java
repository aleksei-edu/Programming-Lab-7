package com.lapin.client;

import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class Client {
    public static final Logger logger
            = LoggerFactory.getLogger(Client.class);

    @SneakyThrows
    public static void main(String[] args) {
        TCPConnection session = new TCPConnection(new NetworkConfigFile());
        session.run();
    }
}
