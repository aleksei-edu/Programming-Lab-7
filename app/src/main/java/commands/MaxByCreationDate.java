package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;

public class MaxByCreationDate extends AbstractCommand{
    public MaxByCreationDate(){
        super("max_by_creation_date","вывести любой объект из коллекции," +
                " значение поля creationDate которого является максимальным");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            if (CollectionManager.getRouteCollection().size() == 0){
                System.out.println("Коллекция пуста.");
            }
            ArrayList<Route> sortArray = new ArrayList<>();
            for (Route index : CollectionManager.getRouteCollection()){
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
