package utility;

import commands.*;
import context.ApplicationContext;
import exception.CommandNotFoundException;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManager {
    //TODO не

    private static Command[] last10Commands = new Command[10];
    private static int last10CommandsIter = 0;

    /**
     * Метод запускает выполнение команды
     * @param userCommand  команда введенная пользователем
     * @param argument  аргумент команды введенной пользователем
     */

    public static void execute(String userCommand, String argument) throws IOException {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) ( obj instanceof Command ? obj : null);
            if (command != null) {
                command.execute(argument);
            } else {
                System.err.println("Команда не найдена!");
            }
        }
        catch (CommandNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    /**
     * Добавляет команду в историю последних 10 команд
     * @param lastCommand последняя введённая команда
     */
    //TODO можно добавить через Stack
    public static void addLastCommand(Command lastCommand){
        last10Commands[last10CommandsIter % 10] = lastCommand;
        last10CommandsIter+=1;
    }

    /**
     * Вывод в консоль 10 последних команд
     */
    public static void printLast10Commands(){
        for(int i = 0; i < 10; i++){
            if (last10Commands[((last10CommandsIter+i) % 10)] == null){
                continue;
            }
            System.out.println(last10Commands[((last10CommandsIter+i) % 10)].getName());
        }
    }

}
