package utility;

import commands.Command;
import context.ApplicationContext;
import exception.CommandNotFoundException;
import lombok.Getter;

import java.io.IOException;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManager {


    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */

    public static void execute(String userCommand, String argument) throws IOException {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                command.execute(argument);
            } else {
                System.err.println("Команда не найдена!");
            }
        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
