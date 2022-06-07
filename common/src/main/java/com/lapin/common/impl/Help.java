package com.lapin.common.impl;


import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.Command;
import com.lapin.common.commands.AccessType;
import com.lapin.network.StatusCodes;
import org.reflections.Reflections;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Команда, выводящая справку по всем доступным командам
 */
@ClassMeta(
        name = "help",
        description = "вывести справку по доступным командам")
public class Help extends AbstractCommand {
    {
        super.accessType = AccessType.ALL;
    }
    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            Reflections scanner = new Reflections("");
            Set<Class<? extends Command>> implementationClasses = scanner.getSubTypesOf(Command.class);
            StringBuilder response = new StringBuilder();
            for (Class<? extends Command> clazz : implementationClasses) {
                ClassMeta annotation = clazz.getAnnotation(ClassMeta.class);
                if (annotation != null) {
                    response.append(annotation.name()).append(" – ").append(annotation.description()).append("\n");
                }
            }
            OutManager.push(StatusCodes.OK, response.toString());

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
