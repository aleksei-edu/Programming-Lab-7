package com.lapin.server.impl;


import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;

/**
 * Команда для выхода из консольного приложения
 */
@ClassMeta(name = "exit", description = "завершить программу (без сохранения в файл)")
public class Exit extends AbstractCommand {

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            System.exit(0);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }

}
