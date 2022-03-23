package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import exception.CommandNeedArgumentException;
import utility.FileManager;

/**
 * Команда считывающая и выполняющая скрипт из файла
 */
@ClassMeta(name = "execute_script", description = "считать и исполнить скрипт из указанного файла.")
public class ExecuteScript extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException();
            FileManager.readScript(argument);
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
