package com.lapin.common.commands.impl;


import com.lapin.common.controllers.CommandManager;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.StatusCodes;


import java.io.Serializable;


/**
 * Команда обновляет значение элемента коллекции
 */
@ClassMeta(name = "update", description = "обновить значение элемента коллекции, id которого равен заданному")
public class Update extends AbstractCommand {
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
            if (rc.getArg().isEmpty()) throw new CommandNeedArgumentException("Введите id элемента, " +
                    "значение которого хотите обновить");
            if(!(rc.argObj() instanceof Route)) throw new RuntimeException("The command ended with an error. Try again.");
            try {
                    int id = Integer.parseInt(rc.getArg());
                    ((Route) rc.argObj()).setId(id);
                    boolean flag = collectionManager.update(id, (Route) rc.argObj(), rc.getUser());
                    if (flag) {
                        OutResultStack.push(StatusCodes.OK,"The element was successfully updated.");
                    }
                    else OutResultStack.push(StatusCodes.OK,"The element was not found.");
            } catch (NumberFormatException e) {
                OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
            }

        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, e.getMessage());
        }
    }
}
