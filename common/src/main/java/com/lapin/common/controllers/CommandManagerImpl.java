package com.lapin.common.controllers;

import com.lapin.common.client.Client;
import com.lapin.common.commands.CheckAccess;
import com.lapin.common.exception.CommandNotFoundException;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.*;
import com.lapin.common.utility.OutResultStack;
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
public class CommandManagerImpl implements CommandManager {
    @Setter
    @Getter
    private DBHandler dbHandler;
    @Setter
    @Getter
    private Client client;
    @Setter
    private Client_IO clientIO;



    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */
    public Pair<StatusCodes,Object> handle(String userCommand, String argument, Serializable argObj) {
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
                        command.execute(new RequestCommand(userCommand, argument,argObj, client.getUser()));
                    }
                    else{
                        Pair response = clientIO.handle(userCommand, argument, argObj, client.getUser());
                        client.setStatusCode((StatusCodes) response.getFirst());
                        OutResultStack.push( (StatusCodes) response.getFirst(), response.getSecond());
                    }
                }
                else {
                    OutResultStack.push(StatusCodes.ERROR,"Access denied!");
                }
            }
            else {
                OutResultStack.push(StatusCodes.ERROR,"Command not found!");
            }
        } catch (CommandNotFoundException e) {
            OutResultStack.push(StatusCodes.ERROR,"Command not found!");
        } catch (Exception e) {
            OutResultStack.push(StatusCodes.ERROR,"Failed to execute command!");
        }
        finally {
            Pair response = OutResultStack.pop();
            client.setStatusCode((StatusCodes) response.getFirst());
            if(response.getSecond() instanceof String){
                System.out.println((String)response.getSecond());
            }
            return response;
        }
    }
    public void execute(String userCommand, String argument, Serializable argObj) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                command.execute(new RequestCommand(userCommand, argument,argObj, client.getUser()));
            }
            else {
                OutResultStack.push(StatusCodes.ERROR,"Command not found!");
            }
        } catch (CommandNotFoundException e) {
            OutResultStack.push(StatusCodes.ERROR,"Command not found!");
        } catch (Exception e) {
            OutResultStack.push(StatusCodes.ERROR,"Failed to execute command!");
        }
    }

}
