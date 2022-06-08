package com.lapin.common.impl;


import com.lapin.common.commands.AccessType;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.CollectionManager;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.common.utility.RemoveLastChar;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.network.StatusCodes;


import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда выводит в консоль все элементы коллекции
 */
@ClassMeta(
        name = "show",
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
public class Show extends AbstractCommand {
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
            } else for (Route index : collectionManager.getRouteCollection()) {
                response += index.toString()+"\n";
            }
            response = RemoveLastChar.remove(response);
            OutManager.push(StatusCodes.OK,response);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
