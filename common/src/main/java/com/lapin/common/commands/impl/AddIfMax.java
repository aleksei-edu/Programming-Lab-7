package com.lapin.common.commands.impl;


import com.lapin.common.controllers.CommandManager;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.data.Route;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;


/**
 * Команда добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 */
@ClassMeta(
        name = "add_if_max",
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции"
)
public class AddIfMax extends AbstractCommand {
    private CollectionManager collectionManager = Controllers.getCollectionManager();
    @Inject
    private CommandManager commandManager;
    {
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }

    @Override
    public void execute(RequestCommand rc) {
        try {
            if(!(rc.argObj() instanceof Route)) throw new RuntimeException();
            String response = "";
            boolean flag = true;
            Route route = (Route) rc.argObj();
            for (Route index : collectionManager.getRouteCollection()) {
                if (route.compareTo(index) < 1) {
                    flag = false;
                }
            }
            if (flag) {
                collectionManager.add(route, commandManager.getClient().getUser());
                response += "Элемент добавлен";
            } else {
                response += "Элемент НЕ добавлен";
            }
            OutResultStack.push(StatusCodes.OK, response);
        } catch (AccessDeniedException e){
            OutResultStack.push(StatusCodes.ERROR, "Access denied");
        }
        catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
