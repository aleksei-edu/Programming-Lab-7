package com.lapin.common.commands.impl;

import com.lapin.common.controllers.Controllers;
import com.lapin.common.data.Route;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;

/**
 * Команда удаляет элемент из коллекции по id
 */
@ClassMeta(name = "remove_by_id", description = "удалить элемент из коллекции по его id")
public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager = Controllers.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
    }


    @Override
    public void execute(RequestCommand rc) {
        try {
            if (rc.getArg().isEmpty()) throw new CommandNeedArgumentException();
            String response = "";
            try {
                int id = Integer.parseInt(rc.getArg());
                Route route1 = (Route)collectionManager
                        .getRouteCollection()
                        .stream()
                        .filter(route -> (route.getId() == id)).findFirst().orElse(null);
                if (route1 == null){
                    response += "Элемент не найден.";
                }
                else {
                    collectionManager.getRouteCollection().remove(route1);
                    response += "Удалён элемент по id: " + id;
                }
                OutResultStack.push(StatusCodes.OK,response);
            } catch (NumberFormatException e) {
                OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }

        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}