package commands.impl;

import annotation.ClassMeta;
import annotation.Inject;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.FileManager;

import java.io.IOException;

/**
 * Команда сохраняет коллекцию в файл
 */

@ClassMeta(name = "save", description = "сохранить коллекцию в файл")
public class Save extends AbstractCommand {
    @Inject
    private FileManager fileManager;

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            fileManager.saveCollection();
        } catch (CommandNotAcceptArgumentsException | IOException e) {
            e.printStackTrace();
        }
    }
}
