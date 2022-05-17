package com.lapin.common.impl;


import com.lapin.common.data.Route;
import com.lapin.common.exception.AccessDeniedException;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Команда выводит минимальный по distance элемент Route
 */
@ClassMeta(name = "min_by_distance", description = "вывести любой объект из коллекции, значение поля distance которого является минимальным")
public class MinByDistance extends AbstractCommand {
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
                response +="Коллекция пуста.";
            }
            ArrayList<Route> sortArray = new ArrayList<>();
            for (Route index : collectionManager.getRouteCollection()) {
                sortArray.add(index);
            }
            Collections.sort(sortArray, new Route.ComparatorByDistance());
            response += sortArray.get(0).toString();
            OutManager.push(StatusCodes.OK,response);
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
