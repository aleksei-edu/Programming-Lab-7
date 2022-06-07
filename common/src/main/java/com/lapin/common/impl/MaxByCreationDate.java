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
import java.util.Collections;
import java.util.HashMap;

/**
 * Команда выводит максимальный по creationDate элемент Route
 */
@ClassMeta(name = "max_by_creation_date", description = "вывести любой объект из коллекции, значение поля creationDate которого является максимальным")
public class MaxByCreationDate extends AbstractCommand {
    private CollectionManager collectionManager = CommandManager.getCollectionManager();
    {
        super.accessType = AccessType.ALL;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            String response = "";
            if (collectionManager.getRouteCollection().size() == 0) {
                response += "Коллекция пуста.";
            }
            ArrayList<Route> sortArray = new ArrayList<>();
            for (Route index : collectionManager.getRouteCollection()) {
                sortArray.add(index);
            }
            Collections.sort(sortArray, new Route.ComparatorByCreationDate());
            response += sortArray.get(sortArray.size() - 1).toString();
            OutManager.push(StatusCodes.OK,response);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
