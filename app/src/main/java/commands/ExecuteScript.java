package commands;

import exception.CommandNotAcceptArgumentsException;
import exception.CommandNeedArgumentException;
import utility.FileManager;

public class ExecuteScript extends AbstractCommand{
    public ExecuteScript(){
        super("execute_script","считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new CommandNeedArgumentException();
            FileManager.readScript(argument);
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
