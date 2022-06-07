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
    {
        super.accessType = AccessType.USER;
        super.executingLocal = true;
    }


    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            OutManager.push(StatusCodes.EXIT_CLIENT, "Client exit");
            System.exit(0);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }

}
