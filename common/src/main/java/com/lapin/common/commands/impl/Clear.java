package com.lapin.common.commands.impl;


import com.lapin.common.controllers.Controllers;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;


/**
 * Команда очистить коллекцию
 */
@ClassMeta(
        name = "clear",
        description = "очистить коллекцию")
public class Clear extends AbstractCommand {
    private CollectionManager collectionManager  = Controllers.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
    }

    @Override
    public void execute(RequestCommand rc) {
        try {
            collectionManager.clear();
            OutResultStack.push(StatusCodes.OK,"Collection is cleared.");
        }catch (CommandNotAcceptArgumentsException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
