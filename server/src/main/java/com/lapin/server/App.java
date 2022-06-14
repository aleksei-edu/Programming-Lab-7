package com.lapin.server;


import com.lapin.common.client.Client;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.FileManager;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.TCPConnection;
import com.lapin.network.conop.ServerTCPConnection;
import com.lapin.network.listener.ServerListener;
import com.lapin.server.utility.JavaCollectionManager;

import java.io.File;

public class App {
    public static void main(String[] args){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        FileManager fileManager = new FileManager();
        fileManager.setEnv("LABA");
        CollectionManager collectionManager = new JavaCollectionManager(fileManager);
        CommandManager.setCollectionManager(collectionManager);
        FileManager.setCollectionManager(collectionManager);
        CommandManager.setFileManager(fileManager);
        collectionManager.loadCollection();
        File configPath = new File("server/src/main/resources/config.properties");
        TCPConnection server = new TCPConnection(new ServerTCPConnection(new ServerRequestHandler(),configPath));
        ServerListener serverListener = (ServerListener) server.start();
        Thread serverThread = new Thread(serverListener);
        Client admin = new Client(configPath,serverListener);
        Thread adminSession = new Thread(admin);
        adminSession.start();
        serverThread.start();
    }
}
