package com.lapin.common.commands.impl;

import com.lapin.common.controllers.Controllers;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;


/**
 * Команда удаляет все элементы по значению поля distance
 */
@ClassMeta(name = "remove_all_by_distance", description = "удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному")
public class RemoveAllByDistance extends AbstractCommand {
    private CollectionManager collectionManager = Controllers.getCollectionManager();;
    {
        super.accessType = AccessType.ALL;
    }


    @Override
    public void execute(RequestCommand rc) {
        try {
            if (rc.getArg().isEmpty()) throw new CommandNeedArgumentException();
            String response = "";
            try {
                Long distance = Long.parseLong(rc.getArg());
                collectionManager.getRouteCollection().removeIf(route -> (route.getDistance().equals(distance)));
                response += "Элементы с distance:" + distance.toString() + " - удалены.";
                OutResultStack.push(StatusCodes.OK,response);
            } catch (NumberFormatException e) {
                OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }

        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
