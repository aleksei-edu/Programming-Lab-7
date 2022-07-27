package com.lapin.common.commands.impl;

import com.lapin.common.controllers.FileManager;
import com.lapin.common.exception.MaxRecursionExceededException;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;


/**
 * Команда считывающая и выполняющая скрипт из файла
 */
@ClassMeta(
        name = "execute_script",
        description = "считать и исполнить скрипт из указанного файла.")
public class ExecuteScript extends AbstractCommand {
    @Inject
    private FileManager fileManager;
    {
        super.accessType = AccessType.ALL;
        super.executingLocal = true;
    }

    @Override
    public void execute(RequestCommand rc) {
        try {
            fileManager.readScript(rc.getArg());
            OutResultStack.push(StatusCodes.OK,"The script is executed");
        } catch (FileNotFoundException e) {
            OutResultStack.push(StatusCodes.ERROR, "File not found");
        }
        catch (MaxRecursionExceededException e){
            OutResultStack.push(StatusCodes.ERROR, "Maximum recursion is exceeded!");
        }
        catch (RuntimeException | IOException e) {
            OutResultStack.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
