package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.ConsoleManager;
import utility.CreateNewElementManager;

import java.util.ArrayList;

public class RemoveLower extends AbstractCommand{
    public RemoveLower(){
        super("remove_lower","удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            Route route = CreateNewElementManager.createNewElement();
            ArrayList<Route> removeArray = new ArrayList<>();
            boolean flag = false;
            for (Route index : CollectionManager.getRouteCollection()){
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
                                CollectionManager.getRouteCollection()
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
