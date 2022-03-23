package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;
import utility.ConsoleManager;
import utility.CreateNewElementManager;

import java.util.ArrayList;

/**
 * Команда удаляет все элементы меньшие, чем переданный ей
 */
@ClassMeta(name = "remove_lower", description = "удалить из коллекции все элементы, меньшие, чем заданный")
public class RemoveLower extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            Route route = CreateNewElementManager.createNewElement();
            ArrayList<Route> removeArray = new ArrayList<>();
            boolean flag = false;
            for (Route index : JavaCollectionManager.getRouteCollection()){
                if(index.compareTo(route) < 1){
                    removeArray.add(index);
                    flag = true;
                }
            }
            if(flag){
                System.out.println("Подтвердите удаление элементов:");
                for(Route index : removeArray){
                    System.out.println(index.toString());
                }
                System.out.println("Введите y/n");
                while (true){
                    try {
                        var userPrint = ConsoleManager.getUserPrint();
                        if (userPrint.equals("y")) {
                            for(Route index : removeArray){
                                JavaCollectionManager.getRouteCollection()
                                        .removeIf(route2Delete -> (route2Delete.getId()== index.getId()));
                            }
                            System.out.println("Элементы успешно удалены");
                            break;
                        } else if (userPrint.equals("n")) {
                            System.out.println("Удаление элементов ОТМЕНЕНО");
                            break;
                        } else throw new IllegalArgumentException("Введено что-то не то. Повторите попытку.");
                    }
                    catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                System.out.println("Нет элементов меньших, чем заданный.");
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
