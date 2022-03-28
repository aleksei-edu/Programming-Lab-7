package commands.impl;

import annotation.ClassMeta;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandNotAcceptArgumentsException;
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
            for (Class<? extends Command> clazz : implementationClasses) {
                ClassMeta annotation = clazz.getAnnotation(ClassMeta.class);
                if (annotation != null) {
                    System.out.println(annotation.name() + " – " + annotation.description());
                }
            }
        } catch (CommandNotAcceptArgumentsException e) {
            e.printStackTrace();
        }
    }
}
