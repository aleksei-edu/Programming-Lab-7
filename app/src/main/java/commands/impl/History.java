package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandNotAcceptArgumentsException;
import utility.HistoryStack;

/**
 * Команда, показывающая 10 последних команд
 */
@ClassMeta(name = "history", description = "вывести последние 10 команд (без их аргументов)")
public class History extends AbstractCommand {
    public History(){
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            for(Command command : HistoryStack.getInstance().last10()){
                System.out.println(command.getName());
            }
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
