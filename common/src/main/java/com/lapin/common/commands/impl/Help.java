package com.lapin.common.commands.impl;


import com.lapin.common.client.Client;
import com.lapin.common.commands.CheckAccess;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.Command;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import org.reflections.Reflections;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Команда, выводящая справку по всем доступным командам
 */
@ClassMeta(
        name = "help",
        description = "вывести справку по доступным командам")
public class Help extends AbstractCommand {
    @Inject
    private Client client;
    {
        super.accessType = AccessType.ALL;
        super.executingLocal =true;
    }
    @Override
    public void execute(String argument, Serializable argObj) {
        try {
            Reflections scanner = new Reflections("");
            Set<Class<? extends AbstractCommand>> implementationClasses = scanner.getSubTypesOf(AbstractCommand.class);
            String response = implementationClasses
                    .stream()
                    .filter(clazz -> {
                        Object obj = null;
                        obj = ApplicationContext.getInstance().getBean(clazz,true,false);
                        Command command = (Command) (obj instanceof Command ? obj : null);
                        try {
                            Field field = clazz.getSuperclass().getDeclaredField("accessType");
                            field.setAccessible(true);
                            boolean flag = CheckAccess.check(client.getClientType(), (AccessType) field.get(command));
                            return flag;
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            return false;
                        }
                    })
                    .map(clazz -> clazz.getAnnotation(ClassMeta.class))
                    .filter(Objects::nonNull)
                    .map(an -> an.name() + " – " + an.description())
                    .sorted()
                    .collect(Collectors.joining("\n"));
            OutManager.push(StatusCodes.OK, response);

        } catch (RuntimeException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
