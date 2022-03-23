package client;

import annotation.Inject;
import factory.BeanFactory;
import utility.ConsoleManager;
import utility.FileManager;
import utility.JavaCollectionManager;

import java.io.IOException;

public class Session {
    @Inject
    private JavaCollectionManager javaCollectionManager;
    public void startUp() throws IOException {
        FileManager.setEnv("LABA");
        javaCollectionManager.loadCollection();
        //TODO: PrintTable
        //TODO: Не выводить stack trace
        //TODO: паттерн команда
        //TODO: больше интерфейсов
        //TODO: Сделать более безопасный exit
        while (true) {
            ConsoleManager.interactiveMode();
        }
    }
}
