package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.JavaCollectionManager;

/**
 * Команда удаляет все элементы по значению поля distance
 */
@ClassMeta(name = "remove_all_by_distance", description = "удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному")
public class RemoveAllByDistance extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new CommandNeedArgumentException();
            try {
                Long distance = Long.parseLong(argument);
                collectionManager.getRouteCollection().removeIf(route -> (route.getDistance().equals(distance)));
                System.out.println("Элементы с distance:" + distance.toString() + " - удалены.");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
