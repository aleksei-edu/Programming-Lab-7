package com.lapin.common.impl;


import com.lapin.common.data.Route;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.CreateNewElementManager;
import com.lapin.server.utility.JavaCollectionManager;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда добавить новый элемент в коллекцию
 */
@ClassMeta(
        name = "add",
        description = "добавить новый элемент в коллекцию")
public class Add extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }
    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if(!(args.get(RequestBodyKeys.ACCESS_TYPE)).equals(accessType))
                throw new AccessDeniedException();
            if(!(args.get(RequestBodyKeys.ARG_OBJ) instanceof Route)) throw new RuntimeException();
            else collectionManager.add((Route) args.get(RequestBodyKeys.ARG_OBJ));
        } catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        }
        catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
