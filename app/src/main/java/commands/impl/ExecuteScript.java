package commands.impl;

import annotation.ClassMeta;
import annotation.Inject;
import commands.AbstractCommand;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.FileManager;

/**
 * Команда считывающая и выполняющая скрипт из файла
 */
@ClassMeta(name = "execute_script", description = "считать и исполнить скрипт из указанного файла.")
public class ExecuteScript extends AbstractCommand {
    @Inject
    private FileManager fileManager;

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new CommandNeedArgumentException();
            fileManager.readScript(argument);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
