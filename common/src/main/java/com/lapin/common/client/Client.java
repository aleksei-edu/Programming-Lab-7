package com.lapin.common.client;

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

public class Client implements Runnable{
    private final NetworkConfigurator config;
    private final ClientType clientType;
    public Client(NetworkConfigurator config){
        this.config = config;
        clientType = config.getClientType();
    }
    public void run(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        CommandManager commandManager = new CommandManager(clientType);
        if (clientType.equals(ClientType.REMOTE)) {
            TCPConnection session = new TCPConnection(config);
            ClientListener listener = (ClientListener) session.start();
            Client_IO client_io = new Client_Network_IO(config, listener);
            commandManager.setClientIO(client_io);
        }
        ConsoleManager consoleManager = new ConsoleManager(commandManager);
        while (true){
            consoleManager.interactiveMode();
        }
    }
}
