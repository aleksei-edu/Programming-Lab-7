package com.lapin.common.utility;

import com.lapin.common.commands.CheckAccess;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotFoundException;
import com.lapin.di.context.ApplicationContext;

import com.lapin.common.commands.Command;

import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManager {
    private static CollectionManager collectionManager;
    private final ClientType clientType;
    private Client_IO clientIO;
    private static final HashSet<String> CLIENT_COMMANDS = new HashSet<>();
    private static final HashSet<String> SERVER_COMMANDS = new HashSet<>();
    private static final HashSet<String> COMMANDS_EXECUTING_WITHOUT_SENDING = new HashSet<>();
    private static final HashSet<String> COMMANDS_NEED_OBJ = new HashSet<>();
    public CommandManager(ClientType clientType){
        this.clientType = clientType;
    }

    public static void setCollectionManager(CollectionManager collectionManager){
        CommandManager.collectionManager = collectionManager;
    }
    public void setClientIO(Client_IO clientIO){
        this.clientIO = clientIO;
    }

    public static CollectionManager getCollectionManager(){
        return collectionManager;
    }

    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */
    public void execute(String userCommand, String argument) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                if(CheckAccess.check(clientType,command.getAccessType())){
                    Route argObj = null;
                    if(command.getNeedObj()){
                        argObj = CreateNewElementManager.createNewElement();
                    }
                    if(command.getExecutingLocal() || clientType.equals(ClientType.LOCAL)){
                        command.execute(argument,argObj);
                    }
                    else{
                        OutManager.push(StatusCodes.OK, clientIO.handle(userCommand, argument, argObj));
                    }
                }
                else OutManager.push(StatusCodes.ERROR,"Access denied!");
            }
            else {
                OutManager.push(StatusCodes.ERROR,"Command not found!");
            }
        } catch (CommandNotFoundException e) {
            OutManager.push(StatusCodes.ERROR,"Command not found!");
        } catch (Exception e) {
            OutManager.push(StatusCodes.ERROR,"Failed to execute command!");
        }
        finally {
            OutManager.println();
        }
    }
    public static void execute(String userCommand, String argument, Serializable argObj) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                command.execute(argument, argObj);
            }
            else {
                OutManager.push(StatusCodes.ERROR,"Command not found!");
            }
        } catch (CommandNotFoundException e) {
            OutManager.push(StatusCodes.ERROR,"Command not found!");
        } catch (Exception e) {
            System.err.println("Failed to execute command!");
        }
    }

}
