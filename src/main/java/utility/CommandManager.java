package utility;

import commands.*;
import exception.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static List<CommandInterface> commands = new ArrayList<>();
    private static CommandInterface[] last10Commands = new CommandInterface[10];
    private static int last10CommandsIter = 0;
    private static CommandInterface show = new ShowCommand();
    private static CommandInterface exit = new ExitCommand();
    private static CommandInterface help = new HelpCommand();
    private static CommandInterface save = new SaveCommand();
    private static CommandInterface clear = new ClearCommand();
    private static CommandInterface history = new HistoryCommand();


    static {
        commands.add(show);
        commands.add(exit);
        commands.add(help);
        commands.add(save);
        commands.add(clear);
        commands.add(history);
    }

    public static void execute(String userCommand, String argument){
        try {
            boolean flag = false;
            for (CommandInterface commandIter : commands) {
                if (commandIter.getName().equals(userCommand)) {
                    commandIter.execute(argument);
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
