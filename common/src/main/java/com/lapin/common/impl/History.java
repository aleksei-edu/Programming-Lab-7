package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.HistoryStack;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.Command;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Команда, показывающая 10 последних команд
 */
@ClassMeta(name = "history", description = "вывести последние 10 команд (без их аргументов)")
public class History extends AbstractCommand {
    {
        super.accessType = AccessType.ALL;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            String response = "";
            for (Command command : HistoryStack.getInstance().last10()) {
                response += command.getName();
            }
            OutManager.push(StatusCodes.OK,response);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
