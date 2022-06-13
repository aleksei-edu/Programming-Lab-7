package com.lapin.common.client;

import com.lapin.common.utility.*;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ClientListener;
import com.lapin.network.listener.ServerListener;
import lombok.Getter;

public class Client implements Runnable{
    private final NetworkConfigurator config;
    @Getter
    private final ClientType clientType;
    private ServerListener serverListener;
    private StatusCodes sc = StatusCodes.OK;
    public Client(NetworkConfigurator config){
        this.config = config;
        clientType = config.getClientType();
    }
    public Client(NetworkConfigurator config, ServerListener serverListener){
        this.serverListener = serverListener;
        this.config = config;
        clientType = config.getClientType();
    }
    public void run(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        CommandManager commandManager = new CommandManager(this);
        if (clientType.equals(ClientType.REMOTE)) {
            TCPConnection session = new TCPConnection(config);
            ClientListener listener = (ClientListener) session.start();
            Client_IO client_io = new Client_Network_IO(config, listener);
            commandManager.setClientIO(client_io);
        }
        ConsoleManager consoleManager = new ConsoleManager(commandManager);
        FileManager.setConsoleManager(consoleManager);
        while (!sc.equals(StatusCodes.EXIT_CLIENT)){
            consoleManager.interactiveMode();
        }
    }
    public void setStatusCode(StatusCodes sc){
        if(sc.equals(StatusCodes.EXIT_SERVER) && serverListener != null){
            serverListener.setServerStatus(sc);
            this.sc = StatusCodes.EXIT_CLIENT;
        }
        else this.sc = sc;
    }
}
