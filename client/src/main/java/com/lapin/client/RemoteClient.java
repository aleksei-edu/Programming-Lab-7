package com.lapin.client;

import com.lapin.common.client.Client;
import com.lapin.common.utility.Client_IO;
import com.lapin.common.utility.Client_Network_IO;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.ConsoleManager;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ClientListener;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteClient {
    public static final Logger logger
            = LoggerFactory.getLogger(RemoteClient.class);

    @SneakyThrows
    public static void main(String[] args) {
        Client user = new Client(new NetworkConfigFile());
        user.run();
    }
}
