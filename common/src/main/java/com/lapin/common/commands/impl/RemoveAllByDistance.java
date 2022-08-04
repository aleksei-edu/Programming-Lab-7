package com.lapin.common.commands.impl;

import com.lapin.common.controllers.Controllers;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Команда удаляет все элементы по значению поля distance
 */
@ClassMeta(name = "remove_all_by_distance", description = "удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному")
public class RemoveAllByDistance extends AbstractCommand {
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
                Long distance = Long.parseLong(rc.getArg());
                var tempArray = collectionManager
                        .getRouteCollection()
                        .stream()
                        .filter(route -> (route.getDistance().equals(distance))).collect(Collectors.toCollection(ArrayList::new));
                for (Route route : tempArray){
                    collectionManager.deleteRouteByID(route.getId(), rc.getUser().getId());
                }
                response += "Routes with distance:" + distance.toString() + " – deleted.";
                OutResultStack.push(StatusCodes.OK,response);
            } catch (NumberFormatException e) {
                OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }

        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, e.getMessage());
        }
    }
}
