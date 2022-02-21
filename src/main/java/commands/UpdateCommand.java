package commands;

import data.Route;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.ConsoleManager;
import utility.CreateNewElementManager;

public class UpdateCommand extends AbstractCommand{
    public UpdateCommand(){
        super("update","обновить значение элемента коллекции, id которого равен заданному" );
    }

    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException("Введите id элемента, " +
                    "значение которого хотите обновить");
            try{
                int id = Integer.parseInt(argument);
                for(Route route : CollectionManager.getRouteCollection()){
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
