package com.lapin.common.commands.impl;


import com.lapin.common.client.Client;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.utility.HistoryQueue;
import com.lapin.common.utility.OutManager;
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
    private Client client;
    {
        super.accessType = AccessType.ALL;
        super.executingLocal = true;
    }

    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            String response = "";
            response = client
                    .getHistory()
                    .last10()
                    .stream()
                    .map(Command::getName)
                    .collect(Collectors.joining("\n"));
            OutManager.push(StatusCodes.OK,response);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
