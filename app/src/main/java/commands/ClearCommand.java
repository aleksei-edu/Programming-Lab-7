package commands;

import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(){
        super("clear","очистить коллекцию");
    }
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            CollectionManager.clear();
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
