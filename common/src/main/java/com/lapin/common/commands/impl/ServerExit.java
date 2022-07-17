package com.lapin.common.commands.impl;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.controllers.FileManager;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.common.exception.NullEnvException;
import com.lapin.common.controllers.CollectionManager;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.controllers.FileManagerImpl;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.network.StatusCodes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

@ClassMeta(
        name = "exit_server",
        description = "завершает работу серверного приложения (с сохранением в файл)")
public class ServerExit extends AbstractCommand {
    @Inject
    private FileManager fileManager;
    @Inject
    private CollectionManager collectionManager;
    {
        super.accessType = AccessType.ADMIN;
        super.executingLocal = true;
    }

    @Override
    public void execute(String arg, Serializable argObj) {
        try{
            String response = fileManager.saveCollection(collectionManager);
            OutManager.push(StatusCodes.EXIT_SERVER,response);
            //System.exit(0);

        } catch (NullEnvException e) {
            OutManager.push(StatusCodes.ERROR, "Failed to find env. Try again.");
        } catch (FileNotFoundException e){
            OutManager.push(StatusCodes.ERROR, "Failed to find file");
        }
        catch (RuntimeException | IOException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
