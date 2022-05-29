package com.lapin.common.impl;


import com.lapin.common.data.Route;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;


import java.io.Serializable;
import java.nio.channels.SelectionKey;
import java.util.HashMap;


/**
 * Команда добавить новый элемент в коллекцию
 */
@ClassMeta(
        name = "add",
        description = "добавить новый элемент в коллекцию")
public class Add extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if(!(argObj instanceof Route)) throw new RuntimeException();
            else collectionManager.add((Route) argObj);
        }
        catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
