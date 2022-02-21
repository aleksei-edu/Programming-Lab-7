package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.CommandManager;

public class HistoryCommand extends AbstractCommand{
    public HistoryCommand(){
        super("history","вывести последние 10 команд (без их аргументов)");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            CommandManager.printLast10Commands();
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
