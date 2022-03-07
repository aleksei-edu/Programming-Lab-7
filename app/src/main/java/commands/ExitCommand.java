package commands;

import exception.CommandNotAcceptArgumentsException;

public class ExitCommand extends AbstractCommand{
    public ExitCommand(){
        super("exit","завершить программу (без сохранения в файл)");
    }
    @Override
    public void execute(String argument){
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            System.exit(0);
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }

}
