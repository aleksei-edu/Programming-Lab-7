package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import exception.CommandNotAcceptArgumentsException;
import utility.JavaCollectionManager;

/**
 * Команда выводит основную информацию о коллекции
 */
@ClassMeta(name = "info", description = "вывести в стандартный поток вывода информацию о коллекции тип, дата инициализации, количество элементов и т.д.")
public class InfoCommand extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            System.out.println("Сведения о коллекции:");
            System.out.println("Тип: " + JavaCollectionManager.getRouteCollection().getClass().getName());
            System.out.println("Дата инициализации: " + JavaCollectionManager.getLastInitTime());
            String saveTime = "";
            if (JavaCollectionManager.getSaveTimeCollection() == null){saveTime = "Коллекция не сохранялась в этой сессии";}
            else saveTime = JavaCollectionManager.getSaveTimeCollection().toString();
            System.out.println("Дата сохранения: " + saveTime);
            System.out.println("Количество элементов: " + JavaCollectionManager.getRouteCollection().size());
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
