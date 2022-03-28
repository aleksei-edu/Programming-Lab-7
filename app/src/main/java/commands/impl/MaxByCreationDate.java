package commands.impl;

import annotation.ClassMeta;
import annotation.Inject;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.JavaCollectionManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Команда выводит максимальный по creationDate элемент Route
 */
@ClassMeta(name = "max_by_creation_date", description = "вывести любой объект из коллекции, значение поля creationDate которого является максимальным")
public class MaxByCreationDate extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if (collectionManager.getRouteCollection().size() == 0){
                System.out.println("Коллекция пуста.");
            }
            ArrayList<Route> sortArray = new ArrayList<>();
            for (Route index : collectionManager.getRouteCollection()){
                sortArray.add(index);
            }
            Collections.sort(sortArray, new Route.ComparatorByCreationDate());
            System.out.println(sortArray.get(sortArray.size()-1).toString());
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
