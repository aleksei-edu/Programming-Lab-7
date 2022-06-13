package com.lapin.server;

import com.lapin.common.client.Client;
import com.lapin.common.impl.Help;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.ResponseCommand;
import com.lapin.common.utility.*;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.StatusCodes;
import com.lapin.network.TCPConnection;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.listener.ServerListener;
import com.lapin.network.obj.ResponseBodyKeys;
import com.lapin.server.config.NetworkConfigFile;
import com.lapin.server.utility.JavaCollectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerRequestHandlerTest {
    @BeforeEach
    void setUp() {
        NetworkConfigurator config = new NetworkConfigFile();
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        FileManager fileManager = new FileManager();
        fileManager.setEnv("LABA");
        CollectionManager collectionManager = new JavaCollectionManager(fileManager);
        CommandManager.setCollectionManager(collectionManager);
        FileManager.setCollectionManager(collectionManager);
        CommandManager.setFileManager(fileManager);
        collectionManager.loadCollection();
        Client admin = new Client(config);
        Thread adminSession = new Thread(admin);
        adminSession.start();
    }

    @Test
    void handleHelp() {
        ServerRequestHandler srh = new ServerRequestHandler();
        RequestCommand requestCommand = new RequestCommand("help","",null);
        ResponseCommand response = (ResponseCommand) srh.handle(requestCommand);
        StatusCodes st_code = (StatusCodes) response.getBody().get(ResponseBodyKeys.STATUS_CODE);
        String ans = (String) response.getBody().get(ResponseBodyKeys.ANSWER);
        Pair res = new Pair(st_code,ans);
        Help help = new Help();
        help.execute("", null);
        assertEquals(OutManager.pop(), res);
    }
}