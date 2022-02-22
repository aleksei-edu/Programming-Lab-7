package utility;

import commands.*;
import exception.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс управляет командами
 */
public class CommandManager {
    private static final List<CommandInterface> commands = new ArrayList<>();
    private static CommandInterface[] last10Commands = new CommandInterface[10];
    private static int last10CommandsIter = 0;
    private static final CommandInterface show = new ShowCommand();
    private static final CommandInterface exit = new ExitCommand();
    private static final CommandInterface help = new HelpCommand();
    private static final CommandInterface save = new SaveCommand();
    private static final CommandInterface clear = new ClearCommand();
    private static final CommandInterface history = new HistoryCommand();
    private static final CommandInterface removeById = new RemoveByIdCommand();
    private static final CommandInterface info = new InfoCommand();
    private static final CommandInterface add = new AddCommand();
    private static final CommandInterface update = new UpdateCommand();
    private static final CommandInterface script = new ExecuteScript();
    private static final CommandInterface addIfMax = new AddIfMax();
    private static final CommandInterface removeLower = new RemoveLower();
    private static final CommandInterface removeAllByDistance = new RemoveAllByDistance();
    private static final CommandInterface minByDistance = new MinByDistance();
    private static final CommandInterface maxByCreationDate = new MaxByCreationDate();

    static {
        commands.add(help);
        commands.add(info);
        commands.add(show);
        commands.add(add);
        commands.add(update);
        commands.add(removeById);
        commands.add(clear);
        commands.add(save);
        commands.add(script);
        commands.add(exit);
        commands.add(addIfMax);
        commands.add(removeLower);
        commands.add(history);
        commands.add(removeAllByDistance);
        commands.add(minByDistance);
        commands.add(maxByCreationDate);
    }

    /**
     * Метод запускает выполнение команды
     * @param userCommand  команда введенная пользователем
     * @param argument  аргумент команды введенной пользователем
     */

    public static void execute(String userCommand, String argument){
        try {
            boolean flag = false;
            for (CommandInterface commandIter : commands) {
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
    public static void addLastCommand(CommandInterface lastCommand){
        last10Commands[last10CommandsIter % 10] = lastCommand;
        last10CommandsIter+=1;
    }

    /**
     * Возвращает массив из последних 10 команд
     * @return массив последних 10 команд
     */
    private static CommandInterface[] getLast10Commands(){
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
    public static List<CommandInterface> getCommands(){
        return commands;
    }
}
