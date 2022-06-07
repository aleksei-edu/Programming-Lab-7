package com.lapin.common.utility;

import com.lapin.common.impl.Help;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandManagerTest {
    CommandManager commandManager;


    @Test
    void executeLocalHelp(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        CommandManager commandManager = new CommandManager(ClientType.LOCAL);

        String consoleOutput;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        PrintStream capture = new PrintStream(outputStream);
        capture.flush();
        System.setOut(capture);
        commandManager.execute("help","");
        consoleOutput = outputStream.toString();
        System.setOut(System.out);

        Help help = new Help();
        help.execute("",null);
        String res = (String) OutManager.pop().getSecond();
        assertEquals(res,consoleOutput);
    }

}