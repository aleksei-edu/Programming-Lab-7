import utility.*;

public class Main {
    public static void main(String[] args){
        FileManager.setEnv("LABA");
        CollectionManager.loadCollection();
        //TODO: PrintTable
        while(true){
            ConsoleManager.interactiveMode();
        }
    }
}

