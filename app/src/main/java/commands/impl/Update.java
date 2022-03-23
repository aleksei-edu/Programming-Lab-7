package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;
import utility.CreateNewElementManager;

/**
 * Команда обновляет значение элемента коллекции
 */
@ClassMeta(name = "update", description = "обновить значение элемента коллекции, id которого равен заданному")
public class Update extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException("Введите id элемента, " +
                    "значение которого хотите обновить");
            try{
                int id = Integer.parseInt(argument);
                for(Route route : JavaCollectionManager.getRouteCollection()){
                    if(route.getId() == id){
                        CreateNewElementManager.update(route);
                    }
                }
            }
            catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
