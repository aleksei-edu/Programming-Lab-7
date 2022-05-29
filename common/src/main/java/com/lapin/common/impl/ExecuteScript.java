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


    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            fileManager.readScript((String) args.get(RequestBodyKeys.ARG));
        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
