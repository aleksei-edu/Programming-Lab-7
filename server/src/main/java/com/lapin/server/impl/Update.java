package com.lapin.server.impl;


import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.CreateNewElementManager;
import com.lapin.server.utility.JavaCollectionManager;


/**
 * Команда обновляет значение элемента коллекции
 */
@ClassMeta(name = "update", description = "обновить значение элемента коллекции, id которого равен заданному")
public class Update extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new CommandNeedArgumentException("Введите id элемента, " +
                    "значение которого хотите обновить");
            try {
                int id = Integer.parseInt(argument);
                for (Route route : collectionManager.getRouteCollection()) {
                    if (route.getId() == id) {
                        CreateNewElementManager.update(route);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
