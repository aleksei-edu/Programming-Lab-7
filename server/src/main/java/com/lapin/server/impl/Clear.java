package com.lapin.server.impl;


import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.JavaCollectionManager;


/**
 * Команда очистить коллекцию
 */
@ClassMeta(name = "clear", description = "очистить коллекцию")
public class Clear extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            collectionManager.clear();
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
