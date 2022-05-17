package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Команда для выхода из консольного приложения
 */
@ClassMeta(
        name = "exit",
        description = "завершить программу (без сохранения в файл)")
public class Exit extends AbstractCommand {
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }

    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if(!(args.get(RequestBodyKeys.ACCESS_TYPE)).equals(accessType))
                throw new AccessDeniedException();
            OutManager.push(StatusCodes.EXIT_CLIENT, "Client exit");
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }

}
