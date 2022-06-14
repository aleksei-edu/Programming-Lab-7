package com.lapin.common.utility;

import com.lapin.common.client.Client;
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
    private static FileManager fileManager;
    private static Client client;
    private Client_IO clientIO;
    private static CommandManager commandManager;
    public CommandManager(Client client){
        CommandManager.client = client;
        commandManager = this;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static Client getClient() {
        return client;
    }

    public static void setCollectionManager(CollectionManager collectionManager){
        CommandManager.collectionManager = collectionManager;
    }
    public static void setFileManager(FileManager fileManager){
        CommandManager.fileManager = fileManager;
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
                client.getHistory().push(command);
                if(CheckAccess.check(client.getClientType(),command.getAccessType())){
                    Route argObj = null;
                    if(command.getNeedObj()){
                        argObj = CreateNewElementManager.createNewElement();
                    }
                    if(command.getExecutingLocal() || client.getClientType().equals(ClientType.LOCAL)){
                        command.execute(argument,argObj);
                    }
                    else{
                        Pair response = clientIO.handle(userCommand, argument, argObj);
                        client.setStatusCode((StatusCodes) response.getFirst());
                        OutManager.push( (StatusCodes) response.getFirst(), (String) response.getSecond());
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
            Pair response = OutManager.pop();
            client.setStatusCode((StatusCodes) response.getFirst());
            System.out.println((String)response.getSecond());
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
            OutManager.push(StatusCodes.ERROR,"Failed to execute command!");
        }
    }

}
