package utility;
import utility.CommandManager;
import java.util.Locale;
import java.util.Scanner;

/**
 * Класс управляет пользовательским вводом.
 */
public class ConsoleManager {
    /**
     * Пользовательский ввод с консоли (по умолчанию).
     */
    private static Scanner userScanner = new Scanner(System.in);

    /**
     * Считывает из пользовательского ввода введенную команду.
     */
    public static void interactiveMode(){
        var userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ",2);
        CommandManager.execute(userCommand[0],userCommand[1]);
    }

    /**
     * Возвращает пользовательский ввод
     * @return Scanner
     */
    public static Scanner getUserScanner(){
        return userScanner;
    }

    /**
     * Возвращает пользовательский ввод очищенный от мусора.
     * @return String - из пользовательского ввода
     */
    public static String getUserPrint(){
        String userPrint = null;
        if (!userScanner.hasNext()){
            setUserScanner(new Scanner(System.in));
            userPrint = userScanner.nextLine().trim().toLowerCase();
        }
        else{ userPrint = userScanner.nextLine().trim().toLowerCase();}
        return userPrint;
    }

    /**
     * Изменить канал получение пользовательского ввода
     * @param userScanner Scanner с пользовательским вводом
     */
    public static void setUserScanner(Scanner userScanner){
        ConsoleManager.userScanner = userScanner;
    }

}
