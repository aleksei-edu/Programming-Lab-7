package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Команда удаляет все элементы меньшие, чем переданный ей
 */
@ClassMeta(name = "remove_lower", description = "удалить из коллекции все элементы, меньшие, чем заданный")
public class RemoveLower extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }
    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            String response = "";
            //Route route = CreateNewElementManager.createNewElement();
            if(!(argObj instanceof Route)) throw new RuntimeException();
            Route route = (Route) argObj;
            ArrayList<Route> removeArray = new ArrayList<>();
            boolean flag = false;
            for (Route index : collectionManager.getRouteCollection()) {
                if (index.compareTo(route) < 1) {
                    removeArray.add(index);
                    flag = true;
                }
            }
            if (flag) {
                while (true) {
                    try {
                        for (Route index : removeArray) {
                            collectionManager.getRouteCollection()
                                    .removeIf(route2Delete -> (route2Delete.getId() == index.getId()));
                        }
                        OutManager.push(StatusCodes.OK,"Элементы успешно удалены");
                        break;
                    } catch (IllegalArgumentException e) {
                        OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
                    }
                }
            } else {
                OutManager.push(StatusCodes.OK,"Нет элементов меньших, чем заданный.");
            }

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
