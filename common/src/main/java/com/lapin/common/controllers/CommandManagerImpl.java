package com.lapin.common.controllers;

import com.lapin.common.client.Client;
import com.lapin.common.commands.CheckAccess;
import com.lapin.common.data.Route;
import com.lapin.common.exception.CommandNotFoundException;
import com.lapin.common.utility.*;
import com.lapin.di.context.ApplicationContext;

import com.lapin.common.commands.Command;

import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManagerImpl {
    private CollectionManager collectionManager;
    private FileManagerImpl fileManagerImpl;
    private Client client;
    @Setter
    @Getter
    private DBHandler dbHandler;
    private Client_IO clientIO;
    private static final CommandManagerImpl COMMAND_MANAGER = new CommandManagerImpl();
    private CommandManagerImpl(){
    }
    public void setClient(Client client){
        COMMAND_MANAGER.client = client;
    }
    public static CommandManagerImpl getInstance() {
        return COMMAND_MANAGER;
    }

    public FileManagerImpl getFileManagerImpl() {
        return fileManagerImpl;
    }

    public Client getClient() {
        return client;
    }

    public void setFileManagerImpl(FileManagerImpl fileManagerImpl){
        COMMAND_MANAGER.fileManagerImpl = fileManagerImpl;
    }
    public void setClientIO(Client_IO clientIO){
        this.clientIO = clientIO;
    }


    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */
    public void execute(String userCommand, String argument, Route argObj) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                client.getHistory().add(command);
                if(CheckAccess.check(client.getClientType(),command.getAccessType())){
                    if(command.getNeedObj() && argObj == null){
                        argObj = CreateNewElementManager.createNewElement();
                    }
                    if(command.getExecutingLocal() || client.getClientType().equals(ClientType.LOCAL)){
                        command.execute(argument,argObj);
                    }
                    else{
                        Pair response = clientIO.handle(userCommand, argument, argObj, client.getUser());
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
