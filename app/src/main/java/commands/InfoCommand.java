package commands;

import exception.CommandNotAcceptArgumentsException;
import utility.CollectionManager;

public class InfoCommand extends AbstractCommand{
    public InfoCommand(){
        super("info","вывести в стандартный поток вывода информацию о коллекции" +
                " (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            System.out.println("Сведения о коллекции:");
            System.out.println("Тип: " + CollectionManager.getRouteCollection().getClass().getName());
            System.out.println("Дата инициализации: " + CollectionManager.getLastInitTime());
            String saveTime = "";
            if (CollectionManager.getSaveTimeCollection() == null){saveTime = "Коллекция не сохранялась в этой сессии";}
            else saveTime = CollectionManager.getSaveTimeCollection().toString();
            System.out.println("Дата сохранения: " + saveTime);
            System.out.println("Количество элементов: " + CollectionManager.getRouteCollection().size());
        }
        catch(CommandNotAcceptArgumentsException e){
            e.printStackTrace();
        }
    }
}
