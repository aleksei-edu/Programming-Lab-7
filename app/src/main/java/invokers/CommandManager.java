package invokers;

import commands.*;
import exception.CommandNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс управляет командами
 */
public class CommandManager {
    //TODO не
    private static final List<Command> commands = new ArrayList<>();
    private static Command[] last10Commands = new Command[10];
    private static int last10CommandsIter = 0;
    static {
        commands.add(new HelpCommand());
        commands.add(new InfoCommand());
        commands.add(new ShowCommand());
        commands.add(new AddCommand());
        commands.add(new UpdateCommand());
        commands.add(new RemoveByIdCommand());
        commands.add(new ClearCommand());
        commands.add(new SaveCommand());
        commands.add(new ExecuteScript());
        commands.add(new ExitCommand());
        commands.add(new AddIfMax());
        commands.add(new RemoveLower());
        commands.add(new HistoryCommand());
        commands.add(new RemoveAllByDistance());
        commands.add(new MinByDistance());
        commands.add(new MaxByCreationDate());
    }

    /**
     * Метод запускает выполнение команды
     * @param userCommand  команда введенная пользователем
     * @param argument  аргумент команды введенной пользователем
     */

    public static void execute(String userCommand, String argument) throws IOException {
        try {
            boolean flag = false;
            for (Command commandIter : commands) {
                if (commandIter.getName().equals(userCommand)) {
                    commandIter.execute(argument.toLowerCase().trim());
                    CommandManager.addLastCommand(commandIter);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                throw new CommandNotFoundException();
            }
        }
        catch (CommandNotFoundException e){
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
     * Возвращает массив из последних 10 команд
     * @return массив последних 10 команд
     */
    private static Command[] getLast10Commands(){
        return last10Commands;
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

    /**
     * Возвращает все доступные команды
     * @return выводит List из всех доступных команд
     */
    public static List<Command> getCommands(){
        return commands;
    }
}
