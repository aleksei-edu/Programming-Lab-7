package com.lapin.server;


import com.lapin.common.client.Client;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.FileManager;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import com.lapin.network.TCPConnection;
import com.lapin.network.listener.ServerListener;
import com.lapin.server.config.NetworkConfigFile;
import com.lapin.server.utility.JavaCollectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final int PORT = 8000;
    private static final int SOTIMEOUT = 60 * 1000;
    private static final NetworkConfigFile config = new NetworkConfigFile();
    public static final Logger logger
            = LoggerFactory.getLogger(App.class);
    public static void main(String[] args){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        FileManager fileManager = new FileManager();
        fileManager.setEnv("LABA");
        CollectionManager collectionManager = new JavaCollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(ClientType.LOCAL);
        CommandManager.setCollectionManager(collectionManager);
        FileManager.setCollectionManager(collectionManager);
        CommandManager.setFileManager(fileManager);
        collectionManager.loadCollection();
        TCPConnection server = new TCPConnection(config);
        ServerListener serverListener = (ServerListener) server.start();
        Thread serverThread = new Thread(serverListener);
        Client admin = new Client(config);
        Thread adminSession = new Thread(admin);
        adminSession.start();
        serverThread.start();
    }
}
