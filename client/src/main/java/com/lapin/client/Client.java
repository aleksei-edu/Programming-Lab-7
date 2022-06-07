package com.lapin.client;

import com.lapin.common.utility.Client_IO;
import com.lapin.common.utility.Client_Network_IO;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.ConsoleManager;
import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ClientListener;
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
        NetworkConfigurator config = new NetworkConfigFile();
        TCPConnection session = new TCPConnection(config);
        ClientListener listener = (ClientListener) session.start();
        Client_IO client_io = new Client_Network_IO(config, listener);
        CommandManager commandManager = new CommandManager(config,client_io);
        ConsoleManager consoleManager = new ConsoleManager(commandManager);
    }
}
