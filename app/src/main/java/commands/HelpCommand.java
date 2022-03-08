package commands;

import exception.CommandNotAcceptArgumentsException;
import invokers.CommandManager;

public class HelpCommand extends AbstractCommand{
    public HelpCommand(){
        super("help","вывести справку по доступным командам");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            for (Command command : CommandManager.getCommands()){
                System.out.println(command.toString());
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
