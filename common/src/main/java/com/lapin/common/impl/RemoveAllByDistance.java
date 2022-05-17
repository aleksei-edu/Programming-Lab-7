package com.lapin.common.impl;

import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.JavaCollectionManager;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда удаляет все элементы по значению поля distance
 */
@ClassMeta(name = "remove_all_by_distance", description = "удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному")
public class RemoveAllByDistance extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if (((String) args.get(RequestBodyKeys.ARG)).isEmpty()) throw new CommandNeedArgumentException();
            String argument = (String) args.get(RequestBodyKeys.ARG);
            String response = "";
            try {
                Long distance = Long.parseLong(argument);
                collectionManager.getRouteCollection().removeIf(route -> (route.getDistance().equals(distance)));
                response += "Элементы с distance:" + distance.toString() + " - удалены.";
                OutManager.push(StatusCodes.OK,response);
            } catch (NumberFormatException e) {
                OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
