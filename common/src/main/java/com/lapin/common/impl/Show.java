package com.lapin.common.impl;


import com.lapin.common.commands.AccessType;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.JavaCollectionManager;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда выводит в консоль все элементы коллекции
 */
@ClassMeta(
        name = "show",
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
public class Show extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            String response = "";
            if (collectionManager.getRouteCollection().size() == 0) {
                response += "Коллекция пуста.";
            } else for (Route index : collectionManager.getRouteCollection()) {
                response += index.toString();
            }
            OutManager.push(StatusCodes.OK,response);
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
