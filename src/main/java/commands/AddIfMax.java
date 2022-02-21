package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.CreateNewElementManager;

public class AddIfMax extends AbstractCommand{
    public AddIfMax(){
        super("add_if_max","добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            Route route = CreateNewElementManager.createNewElement();
            boolean flag = true;
            for (Route index : CollectionManager.getRouteCollection()){
                if(route.compareTo(index) < 1){
                    flag = false;
                }
            }
            if(flag){
                CollectionManager.getRouteCollection().add(route);
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
