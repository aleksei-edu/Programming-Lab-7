package com.lapin.server.impl;


import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.CreateNewElementManager;


/**
 * Команда добавить новый элемент в коллекцию
 */
@ClassMeta(name = "add", description = "добавить новый элемент в коллекцию")
public class Add extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            try {
                CreateNewElementManager.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
