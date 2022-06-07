package com.lapin.common.impl;

import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.FileManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.di.annotation.Inject;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;



import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Команда сохраняет коллекцию в файл
 */

@ClassMeta(name = "save", description = "сохранить коллекцию в файл")
public class Save extends AbstractCommand {
    private FileManager fileManager;
    private CollectionManager collectionManager;
    {
        super.accessType = AccessType.ADMIN;
        super.executingLocal = true;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            fileManager.saveCollection(collectionManager);

        } catch (RuntimeException | IOException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
