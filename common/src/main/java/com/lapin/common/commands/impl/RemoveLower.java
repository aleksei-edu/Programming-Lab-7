package com.lapin.common.commands.impl;


import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;



import java.io.Serializable;

/**
 * Команда удаляет все элементы меньшие, чем переданный ей
 */
@ClassMeta(name = "remove_lower", description = "удалить из коллекции все элементы, меньшие, чем заданный")
public class RemoveLower extends AbstractCommand {
    private CollectionManager collectionManager = Controllers.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }
    @Override
    public void execute(RequestCommand rc) {
        try {
            String response = "";
            if(!(rc.argObj() instanceof Route)) throw new RuntimeException();
            Route route = (Route) rc.argObj();
            collectionManager
                    .getRouteCollection()
                    .removeIf(route1 -> (route1.compareTo(route)<1));
            OutResultStack.push(StatusCodes.OK,"Элементы успешно удалены");
        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
