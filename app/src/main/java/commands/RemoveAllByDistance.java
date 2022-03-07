package commands;

import exception.CommandNeedArgumentException;
import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

public class RemoveAllByDistance extends AbstractCommand{
    public RemoveAllByDistance(){
        super("remove_all_by_distance","удалить из коллекции все элементы, " +
                "значение поля distance которого эквивалентно заданному");
    }

    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException();
            try{
                Long distance = Long.parseLong(argument);
                CollectionManager.getRouteCollection().removeIf(route -> (route.getDistance().equals(distance)));
                System.out.println("Элементы с distance:" + distance.toString() + " - удалены.");
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
