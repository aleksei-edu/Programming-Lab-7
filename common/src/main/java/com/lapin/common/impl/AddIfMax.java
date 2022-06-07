package com.lapin.common.impl;


import com.lapin.common.data.Route;
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
 * Команда добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 */
@ClassMeta(
        name = "add_if_max",
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции"
)
public class AddIfMax extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            //Route route = CreateNewElementManager.createNewElement();
            if(!(argObj instanceof Route)) throw new RuntimeException();
            String responce = "";
            boolean flag = true;
            Route route = (Route) argObj;
            for (Route index : collectionManager.getRouteCollection()) {
                if (route.compareTo(index) < 1) {
                    flag = false;
                }
            }
            if (flag) {
                collectionManager.add(route);
                responce += "Элемент добавлен";
            } else {
                responce += "Элемент НЕ добавлен";
            }
            OutManager.push(StatusCodes.OK, responce);
        } catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        }
        catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
