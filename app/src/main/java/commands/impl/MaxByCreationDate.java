package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Команда выводит максимальный по creationDate элемент Route
 */
@ClassMeta(name = "max_by_creation_date", description = "вывести любой объект из коллекции, значение поля creationDate которого является максимальным")
public class MaxByCreationDate extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if (JavaCollectionManager.getRouteCollection().size() == 0){
                System.out.println("Коллекция пуста.");
            }
            ArrayList<Route> sortArray = new ArrayList<>();
            for (Route index : JavaCollectionManager.getRouteCollection()){
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
