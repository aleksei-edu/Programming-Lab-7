package com.lapin.server.impl;


import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.JavaCollectionManager;


/**
 * Команда выводит в консоль все элементы коллекции
 */
@ClassMeta(name = "show", description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
public class Show extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            String response = "";
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if (collectionManager.getRouteCollection().size() == 0) {
                response += "Коллекция пуста.";
            } else for (Route index : collectionManager.getRouteCollection()) {
                response += index.toString();
            }
            OutManager.push(response);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
