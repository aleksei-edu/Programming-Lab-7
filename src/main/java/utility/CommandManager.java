package utility;

import commands.*;
import exception.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandManager {
    private static List<CommandInterface> commands = new ArrayList<>();
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
    }

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
    public static void addLastCommand(CommandInterface lastCommand){
        last10Commands[last10CommandsIter % 10] = lastCommand;
        last10CommandsIter+=1;
    }

    public static CommandInterface[] getLast10Commands(){
        return last10Commands;
    }

    public static void printLast10Commands(){
        for(int i = 0; i < 10; i++){
            if (last10Commands[((last10CommandsIter+i) % 10)] == null){
                continue;
            }
            System.out.println(last10Commands[((last10CommandsIter+i) % 10)].getName());
        }
    }

    public static List<CommandInterface> getCommands(){
        return commands;
    }
}
