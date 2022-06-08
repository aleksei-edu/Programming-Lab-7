package com.lapin.common.utility;

import com.lapin.common.impl.Help;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.ClientType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleManagerTest {

    @Test
    void interactiveModeLocalHelp() {
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        ConsoleManager consoleManager = new ConsoleManager(new CommandManager(ClientType.LOCAL));
        String consoleInput = "help";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);
        ConsoleManager.setUserScanner(scanner);
        String consoleOutput;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        PrintStream capture = new PrintStream(outputStream);
        System.setOut(capture);
        consoleManager.interactiveMode();
        capture.flush();
        consoleOutput = outputStream.toString();
        System.setOut(System.out);
        System.setIn(System.in);
        Help help =new Help();
        help.execute("",null);
        assertEquals(OutManager.pop().getSecond(),consoleOutput);
    }
}