package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;

/**
 * Команда очистить коллекцию
 */
@ClassMeta(name = "clear", description = "очистить коллекцию")
public class Clear extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            JavaCollectionManager.clear();
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
