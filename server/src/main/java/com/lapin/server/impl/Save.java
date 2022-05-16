package com.lapin.server.impl;

import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.di.annotation.Inject;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.FileManager;


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
