package commands;

import exception.CommandNotAcceptArgumentsException;
import utility.CreateNewElementManager;

public class AddCommand extends AbstractCommand{
    public AddCommand(){
        super("add","добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            try{
                CreateNewElementManager.add();
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
