package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.JavaCollectionManager;

/**
 * Команда выводит в консоль все элементы коллекции
 */
@ClassMeta(name = "show", description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
public class Show extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if (collectionManager.getRouteCollection().size() == 0) {
                System.out.println("Коллекция пуста.");
            } else for (Route index : collectionManager.getRouteCollection()) {
                System.out.println(index.toString());
            }
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
