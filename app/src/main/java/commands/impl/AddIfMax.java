package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;
import utility.CreateNewElementManager;

/**
 * Команда добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 */
@ClassMeta(name = "add_if_max", description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции")
public class AddIfMax extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            Route route = CreateNewElementManager.createNewElement();
            boolean flag = true;
            for (Route index : JavaCollectionManager.getRouteCollection()){
                if(route.compareTo(index) < 1){
                    flag = false;
                }
            }
            if(flag){
                JavaCollectionManager.getRouteCollection().add(route);
                System.out.println("Элемент добавлен");
            }
            else{
                System.out.println("Элемент НЕ добавлен");
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
