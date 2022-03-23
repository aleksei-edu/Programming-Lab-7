package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.CreateNewElementManager;

/**
 * Команда добавить новый элемент в коллекцию
 */
@ClassMeta(name = "add", description = "добавить новый элемент в коллекцию")
public class Add extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            try{
                CreateNewElementManager.add();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
