package com.lapin.common.commands.impl;


import com.lapin.common.controllers.CommandManager;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.data.Route;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.di.annotation.Inject;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;


import java.io.Serializable;


/**
 * Команда добавить новый элемент в коллекцию
 */
@ClassMeta(
        name = "add",
        description = "добавить новый элемент в коллекцию")
public class Add extends AbstractCommand {
    private CollectionManager collectionManager = Controllers.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }
    @Override
    public void execute(RequestCommand rc) {
        try {
            if(!(rc.argObj() instanceof Route)) throw new RuntimeException();
            else{
                collectionManager.add((Route) rc.argObj(), rc.getUser());
            }
            OutResultStack.push(StatusCodes.OK,"The element is added");
        }
        catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
