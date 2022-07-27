package com.lapin.common.commands.impl;


import com.lapin.common.controllers.CommandManager;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.Command;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
import java.util.stream.Collectors;


/**
 * Команда, показывающая 10 последних команд
 */
@ClassMeta(name = "history", description = "вывести последние 10 команд (без их аргументов)")
public class History extends AbstractCommand {
    @Inject
    private CommandManager commandManager;
    {
        super.accessType = AccessType.ALL;
        super.executingLocal = true;
    }

    @Override
    public void execute(RequestCommand rc) {
        try {
            String response = "";
            response = commandManager
                    .getClient()
                    .getHistory()
                    .last10()
                    .stream()
                    .map(Command::getName)
                    .collect(Collectors.joining("\n"));
            OutResultStack.push(StatusCodes.OK,response);

        } catch (RuntimeException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
