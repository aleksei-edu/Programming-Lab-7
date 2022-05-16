package com.lapin.server.impl;


import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.Command;
import org.reflections.Reflections;


import java.util.Set;

/**
 * Команда, выводящая справку по всем доступным командам
 */
@ClassMeta(name = "help", description = "вывести справку по доступным командам")
public class Help extends AbstractCommand {


    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new CommandNotAcceptArgumentsException();
            Reflections scanner = new Reflections("commands");
            Set<Class<? extends Command>> implementationClasses = scanner.getSubTypesOf(Command.class);
            String response = "";
            for (Class<? extends Command> clazz : implementationClasses) {
                ClassMeta annotation = clazz.getAnnotation(ClassMeta.class);
                if (annotation != null) {
                    response += annotation.name() + " – " + annotation.description();
                }
            }
            OutManager.push(response);
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
