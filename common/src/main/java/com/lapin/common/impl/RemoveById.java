package com.lapin.common.impl;

import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Команда удаляет элемент из коллекции по id
 */
@ClassMeta(name = "remove_by_id", description = "удалить элемент из коллекции по его id")
public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();


    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            if (argument.isEmpty()) throw new CommandNeedArgumentException();
            String response = "";
            try {
                int id = Integer.parseInt(argument);
                collectionManager.getRouteCollection().removeIf(route -> (route.getId() == id));
                response += "Удалён элемент по id: " + id;
                OutManager.push(StatusCodes.OK,response);
            } catch (NumberFormatException e) {
                OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
