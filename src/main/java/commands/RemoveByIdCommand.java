package commands;

import data.Route;
import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;
import utility.CommandManager;

public class RemoveByIdCommand extends AbstractCommand{
    public RemoveByIdCommand(){
        super("remove_by_id","удалить элемент из коллекции по его id");
    }
    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException();
            try{
                int id = Integer.parseInt(argument);
                CollectionManager.getRouteCollection().removeIf(route -> (route.getId()==id));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
