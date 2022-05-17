package com.lapin.common.impl;

import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.di.annotation.Inject;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;
import com.lapin.server.utility.FileManager;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда считывающая и выполняющая скрипт из файла
 */
@ClassMeta(
        name = "execute_script",
        description = "считать и исполнить скрипт из указанного файла.")
public class ExecuteScript extends AbstractCommand {
    @Inject
    private FileManager fileManager;
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if(!(args.get(RequestBodyKeys.ACCESS_TYPE)).equals(accessType))
                throw new AccessDeniedException();
            fileManager.readScript((String) args.get(RequestBodyKeys.ARG));
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
