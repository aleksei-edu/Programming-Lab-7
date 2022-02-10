package utility;

import utility.CommandManager;

import java.util.Scanner;

public class ConsoleManager {
    private static Scanner userScanner = new Scanner(System.in);

    public static void interactiveMode(){
        var userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ",2);
        CommandManager.execute(userCommand[0],userCommand[1]);
    }

}
