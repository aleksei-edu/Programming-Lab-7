import utility.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileManager.setEnv("LABA");
        CollectionManager.loadCollection();
        //TODO: PrintTable
        //TODO: Не выводить stack trace
        //TODO: паттерн команда
        //TODO: больше интерфейсов
        //TODO: Сделать более безопасный exit
        while(true){
            ConsoleManager.interactiveMode();
        }
    }
}

