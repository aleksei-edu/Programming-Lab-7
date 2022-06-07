package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда очистить коллекцию
 */
@ClassMeta(
        name = "clear",
        description = "очистить коллекцию")
public class Clear extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            collectionManager.clear();
        }catch (CommandNotAcceptArgumentsException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
