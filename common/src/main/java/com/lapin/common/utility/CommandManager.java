package com.lapin.common.utility;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.exception.CommandNotFoundException;
import com.lapin.common.impl.*;
import com.lapin.common.interaction.Response;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.di.context.ApplicationContext;

import com.lapin.common.commands.Command;

import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import com.lapin.network.config.NetworkConfigurator;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManager {
    private static CollectionManager collectionManager;
    private static final HashSet<String> CLIENT_COMMANDS = new HashSet<>();
    private static final HashSet<String> SERVER_COMMANDS = new HashSet<>();
    private static final HashSet<String> COMMANDS_EXECUTING_WITHOUT_SENDING = new HashSet<>();
    private static final HashSet<String> COMMANDS_NEED_OBJ = new HashSet<>();

    static {
        CLIENT_COMMANDS.add("add");
        CLIENT_COMMANDS.add("add_if_max");
        CLIENT_COMMANDS.add("clear");
        CLIENT_COMMANDS.add("execute_script");
        CLIENT_COMMANDS.add("help");
        CLIENT_COMMANDS.add("history");
        CLIENT_COMMANDS.add("info");
        CLIENT_COMMANDS.add("max_by_creation_date");
        CLIENT_COMMANDS.add("min_by_distance");
        CLIENT_COMMANDS.add("remove_all_by_distance");
        CLIENT_COMMANDS.add("remove_by_id");
        CLIENT_COMMANDS.add("remove_lower");
        CLIENT_COMMANDS.add("show");
        CLIENT_COMMANDS.add("update");
        CLIENT_COMMANDS.add("exit");

        SERVER_COMMANDS.add("save");

        COMMANDS_EXECUTING_WITHOUT_SENDING.add("exit");
        COMMANDS_EXECUTING_WITHOUT_SENDING.add("save");
        COMMANDS_EXECUTING_WITHOUT_SENDING.add("execute_script");

        COMMANDS_NEED_OBJ.add("add");
        COMMANDS_NEED_OBJ.add("add_if_max");
        COMMANDS_NEED_OBJ.add("update");


    }

    public static void setCollectionManager(CollectionManager collectionManager){
        CommandManager.collectionManager = collectionManager;
    }

    public static CollectionManager getCollectionManager(){
        return collectionManager;
    }
    public static RequestCommand processCommand(String userCommand, String argument,NetworkConfigurator config){
        HashSet<String> commands;
        if(config.getClientType().equals(ClientType.ADMIN)){
            commands = CLIENT_COMMANDS;
        }
        else commands = SERVER_COMMANDS;
        return CommandManager.execute(userCommand,argument, commands);
    }

    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */
    public static RequestCommand execute(String userCommand, String argument, HashSet<String> commands) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                //command.execute(argument, argObj);
                if(commands.contains(command.getName())){
                    if(COMMANDS_NEED_OBJ.contains(command.getName())){

                    }
                    if(COMMANDS_EXECUTING_WITHOUT_SENDING.contains(command.getName())){
                        command.execute(argument,null);
                    }
                    else{
                        RequestCommand request = new RequestCommand(command.getName(), argument,null);
                        return request;
                    }
                }
                else OutManager.push(StatusCodes.ERROR,"Command not found!");
            }
            else {
                OutManager.push(StatusCodes.ERROR,"Command not found!");
            }
        } catch (CommandNotFoundException e) {
            OutManager.push(StatusCodes.ERROR,"Command not found!");
            return null;
        } catch (Exception e) {
            System.err.println("Не удалось исполнить команду");
            return null;
        }
        return null;
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
            System.err.println("Не удалось исполнить команду");
        }
    }
}
