package com.lapin.server.impl;

import com.lapin.common.exception.CommandNeedArgumentException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.di.annotation.Inject;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.FileManager;


/**
 * Команда считывающая и выполняющая скрипт из файла
 */
@ClassMeta(name = "execute_script", description = "считать и исполнить скрипт из указанного файла.")
public class ExecuteScript extends AbstractCommand {
    @Inject
    private FileManager fileManager;

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new CommandNeedArgumentException();
            fileManager.readScript(argument);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
