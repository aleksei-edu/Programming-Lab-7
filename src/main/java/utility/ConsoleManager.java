package utility;
import utility.CommandManager;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleManager {
    private static Scanner userScanner = new Scanner(System.in);

    public static void interactiveMode(){
        var userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ",2);
        CommandManager.execute(userCommand[0],userCommand[1]);
    }

    public static Scanner getUserScanner(){
        return userScanner;
    }

    public static String getUserPrint(){
        String userPrint = null;
        if (!userScanner.hasNext()){
            setUserScanner(new Scanner(System.in));
            userPrint = userScanner.nextLine().trim().toLowerCase();
        }
        else{ userPrint = userScanner.nextLine().trim().toLowerCase();}
        return userPrint;
    }

    public static void setUserScanner(Scanner userScanner){
        ConsoleManager.userScanner = userScanner;
    }

}
