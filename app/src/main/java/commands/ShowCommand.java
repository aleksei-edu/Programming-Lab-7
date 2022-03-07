package commands;

import data.Route;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

public class ShowCommand extends AbstractCommand{
    public ShowCommand(){
        super("show","вывести в стандартный поток вывода все " +
                "элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
                if (CollectionManager.getRouteCollection().size() == 0){
                    System.out.println("Коллекция пуста.");
                }
                else for (Route index : CollectionManager.getRouteCollection()){
                    System.out.println(index.toString());
                }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
