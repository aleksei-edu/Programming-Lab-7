package com.lapin.server;


import com.lapin.common.client.Client;
import com.lapin.common.controllers.*;
import com.lapin.common.encrypt.Encryptor;
import com.lapin.common.encrypt.SHA256Encryptor;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.TCPConnection;
import com.lapin.network.conop.ServerTCPConnection;
import com.lapin.network.listener.ServerListener;
import com.lapin.server.db.DBConnector;
import com.lapin.server.db.DBHandlerImpl;
import com.lapin.server.db.DBInitializer;
import com.lapin.server.utility.JavaCollectionManager;
import lombok.Getter;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class App {
    @Getter
    private static DBHandler dbHandler;
    public static void main(String[] args) throws URISyntaxException {
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        DBConnector dbConnector = ApplicationContext.getInstance().getBean(DBConnector.class);
        DBInitializer dbInitializer = new DBInitializer(dbConnector);
        Encryptor encryptor = ApplicationContext.getInstance().getBean(SHA256Encryptor.class);
        dbHandler = new DBHandlerImpl(dbConnector,encryptor);
        dbInitializer.init();
        FileManagerImpl fileManagerImpl = new FileManagerImpl();
        fileManagerImpl.setEnv("LABA");
        CollectionManager collectionManager = ApplicationContext.getInstance().getBean(JavaCollectionManager.class);
        FileManager fileManager = ApplicationContext.getInstance().getBean(FileManager.class);
        fileManager.setCollectionManager(collectionManager);
        CommandManagerImpl.getInstance().setFileManagerImpl(fileManagerImpl);
        collectionManager.loadCollection();
        URL config = App.class.getClassLoader().getResource("config.properties");
        File resources = new File(config.toURI());
        TCPConnection server = new TCPConnection(new ServerTCPConnection(new ServerRequestHandler(),resources));
        ServerListener serverListener = (ServerListener) server.start();
        Thread serverThread = new Thread(serverListener);
        Client admin = new Client(resources,serverListener);
        Thread adminSession = new Thread(admin);
        adminSession.start();
        serverThread.start();
    }
}
