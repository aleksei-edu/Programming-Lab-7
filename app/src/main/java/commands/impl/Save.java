package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.FileManager;
import java.io.IOException;

/**
 * Команда сохраняет коллекцию в файл
 */

@ClassMeta(name = "save", description = "сохранить коллекцию в файл")
public class Save extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            FileManager.saveCollection();
        }
        catch(CommandNotAcceptArgumentsException | IOException e){
            e.printStackTrace();
        }
    }
}
