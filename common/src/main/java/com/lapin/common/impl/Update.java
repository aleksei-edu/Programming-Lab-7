package com.lapin.common.impl;


import com.lapin.common.commands.AccessType;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.CreateNewElementManager;
import com.lapin.server.utility.JavaCollectionManager;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда обновляет значение элемента коллекции
 */
@ClassMeta(name = "update", description = "обновить значение элемента коллекции, id которого равен заданному")
public class Update extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if (((String) args.get(RequestBodyKeys.ARG)).isEmpty()) throw new CommandNeedArgumentException("Введите id элемента, " +
                    "значение которого хотите обновить");
            try {
                String argument = (String) args.get(RequestBodyKeys.ARG);
                int id = Integer.parseInt(argument);
                for (Route route : collectionManager.getRouteCollection()) {
                    if (route.getId() == id) {
                        CreateNewElementManager.update(route);
                    }
                }
                OutManager.push(StatusCodes.OK,"The element was successfully updated.");
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
