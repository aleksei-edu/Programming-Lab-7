package com.lapin.common.utility;

import com.lapin.common.client.Client;
import com.lapin.common.impl.Help;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandManagerTest {
    CommandManager commandManager;


    @Test
    void executeLocalHelp(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        NetworkConfigurator config = new NetworkConfigurator() {};
        Client client = new Client(config);
        CommandManager commandManager = new CommandManager(client);

        String consoleOutput;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        PrintStream capture = new PrintStream(outputStream);
        System.setOut(capture);
        commandManager.execute("help","");
        consoleOutput = outputStream.toString();
        capture.flush();
        System.setOut(System.out);

        Help help = new Help();
        help.execute("",null);
        String res = (String) OutManager.pop().getSecond();
        assertEquals(res+'\n',consoleOutput);
    }

}