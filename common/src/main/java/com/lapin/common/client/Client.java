package com.lapin.common.client;

import com.lapin.common.client.clientpostprocessor.ClientPostProcessor;
import com.lapin.common.controllers.*;
import com.lapin.common.data.User;
import com.lapin.common.utility.*;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import com.lapin.network.TCPConnection;
import com.lapin.network.conop.ClientTCPConnection;
import com.lapin.network.listener.ClientListener;
import com.lapin.network.listener.ServerListener;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Client implements Runnable{
    @Getter
    private final ClientType clientType;
    @Getter
    @Setter
    private User user;
    private final HistoryQueue historyQueue;
    private ServerListener serverListener;
    private Properties properties;
    private File resources;
    private StatusCodes sc = StatusCodes.OK;
    public Client(File resources, ServerListener serverListener){
        this(resources);
        this.serverListener = serverListener;
    }
    public Client(File resources){
        this.resources = resources;
        historyQueue = new HistoryQueue();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(resources));
        } catch (IOException e) {
            System.err.println("Не удалось загрузить config");
        }
        if(properties.getProperty("ClientType").equals("Local")){
            clientType = ClientType.LOCAL;
        }else clientType = ClientType.REMOTE;
    }
    public void run(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        CommandManager commandManager = ApplicationContext.getInstance().getBean(CommandManager.class);
        if (clientType.equals(ClientType.REMOTE)) {
            TCPConnection session = new TCPConnection(new ClientTCPConnection(resources));
            ClientListener listener = (ClientListener) session.start();
            Client_IO client_io = new Client_Network_IO(listener);
            commandManager.setClientIO(client_io);
        }
        commandManager.setClient(this);
        ConsoleManager consoleManager = ApplicationContext.getInstance().getBean(ConsoleManager.class);
        callPostProcessor();
        while (!sc.equals(StatusCodes.EXIT_CLIENT)){
            consoleManager.interactiveMode();
        }
    }
    public void callPostProcessor() {
        ApplicationContext
                .getInstance()
                .getBeanFactory()
                .getBeanConfigurator()
                .getScanner()
                .getSubTypesOf(ClientPostProcessor.class)
                .forEach(processor -> {
                    try {
                        processor.getDeclaredConstructor().newInstance().process(this);
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });
    }
    public void setStatusCode(StatusCodes sc){
        if(sc.equals(StatusCodes.EXIT_SERVER) && serverListener != null){
            serverListener.setServerStatus(sc);
            this.sc = StatusCodes.EXIT_CLIENT;
        }
        else this.sc = sc;
    }
    public HistoryQueue getHistory(){
        return historyQueue;
    }
}
