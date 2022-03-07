package commands;

import exception.CommandNotAcceptArgumentsException;
import utility.FileManager;

public class SaveCommand extends AbstractCommand{
    public SaveCommand(){
        super("save","сохранить коллекцию в файл");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            FileManager.saveCollection();
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
