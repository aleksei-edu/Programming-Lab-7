package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.JavaCollectionManager;

/**
 * Команда удаляет элемент из коллекции по id
 */
@ClassMeta(name = "remove_by_id", description = "удалить элемент из коллекции по его id")
public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException();
            try{
                int id = Integer.parseInt(argument);
                collectionManager.getRouteCollection().removeIf(route -> (route.getId()==id));
                System.out.println("Удалён элемент по id: " + id);
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
