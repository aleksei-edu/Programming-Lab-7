package com.lapin.server.impl;


import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.JavaCollectionManager;


/**
 * Команда выводит основную информацию о коллекции
 */
@ClassMeta(name = "info", description = "вывести в стандартный поток вывода информацию о коллекции тип, дата инициализации, количество элементов и т.д.")
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            String response = "";
            response += "Сведения о коллекции:";
            response += "Тип: " + collectionManager.getRouteCollection().getClass().getName();
            response += "Дата инициализации: " + collectionManager.getLastInitTime();
            String saveTime = "";
            if (collectionManager.getSaveTimeCollection() == null) {
                saveTime = "Коллекция не сохранялась в этой сессии";
            } else saveTime = collectionManager.getSaveTimeCollection().toString();
            response += "Дата сохранения: " + saveTime;
            response += "Количество элементов: " + collectionManager.getRouteCollection().size();
            OutManager.push(response);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
