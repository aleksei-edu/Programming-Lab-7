package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Команда выводит минимальный по distance элемент Route
 */
@ClassMeta(name = "min_by_distance", description = "вывести любой объект из коллекции, значение поля distance которого является минимальным")
public class MinByDistance extends AbstractCommand {

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
            Collections.sort(sortArray, new Route.ComparatorByDistance());
            System.out.println(sortArray.get(0).toString());
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
