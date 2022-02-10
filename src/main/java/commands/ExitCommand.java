package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

public class ExitCommand extends AbstractCommand{
    public ExitCommand(){
        super("exit","Команда для завершения программы.");
    }
    @Override
    public void execute(String argument){
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            System.exit(0);
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }

}
