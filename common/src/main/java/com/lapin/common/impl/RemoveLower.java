package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.CollectionManager;
import com.lapin.server.utility.ConsoleManager;
import com.lapin.server.utility.CreateNewElementManager;
import com.lapin.server.utility.JavaCollectionManager;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Команда удаляет все элементы меньшие, чем переданный ей
 */
@ClassMeta(name = "remove_lower", description = "удалить из коллекции все элементы, меньшие, чем заданный")
public class RemoveLower extends AbstractCommand {
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            String response = "";
            Route route = CreateNewElementManager.createNewElement();
            ArrayList<Route> removeArray = new ArrayList<>();
            boolean flag = false;
            for (Route index : collectionManager.getRouteCollection()) {
                if (index.compareTo(route) < 1) {
                    removeArray.add(index);
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("Подтвердите удаление элементов:");
                for (Route index : removeArray) {
                    System.out.println(index.toString());
                }
                System.out.println("Введите y/n");
                while (true) {
                    try {
                        var userPrint = ConsoleManager.getUserPrint();
                        if (userPrint.equals("y")) {
                            for (Route index : removeArray) {
                                collectionManager.getRouteCollection()
                                        .removeIf(route2Delete -> (route2Delete.getId() == index.getId()));
                            }
                            System.out.println("Элементы успешно удалены");
                            break;
                        } else if (userPrint.equals("n")) {
                            System.out.println("Удаление элементов ОТМЕНЕНО");
                            break;
                        } else throw new IllegalArgumentException("Введено что-то не то. Повторите попытку.");
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Нет элементов меньших, чем заданный.");
            }
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
