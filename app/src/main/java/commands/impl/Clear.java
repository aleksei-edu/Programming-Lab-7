package commands.impl;

import annotation.ClassMeta;
import annotation.Inject;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.JavaCollectionManager;

/**
 * Команда очистить коллекцию
 */
@ClassMeta(name = "clear", description = "очистить коллекцию")
public class Clear extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance() ;

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            collectionManager.clear();
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
