package com.lapin.common.impl;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.commands.AccessType;
import com.lapin.common.commands.Command;
import com.lapin.common.data.Route;
import com.lapin.common.exception.AccessDeniedException;
import com.lapin.common.exception.CommandNotAcceptArgumentsException;
import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.network.StatusCodes;
import org.reflections.Reflections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
@ClassMeta(
        name = "get_accessible_cmds",
        description = "вывести доступные команды")
public class GetAccessibleCmds extends AbstractCommand {
    private AccessType accessType = AccessType.ALL;
    {
        super.setAccessType(accessType);
    }
    @Override
    public void execute(HashMap<RequestBodyKeys,Serializable> args) {
        try {
            if(!(args.get(RequestBodyKeys.ARG_OBJ) instanceof AccessType)) throw new RuntimeException();
            Reflections scanner = new Reflections("commands");
            Set<Class<? extends Command>> implementationClasses = scanner.getSubTypesOf(Command.class);
            String response = "";
            for (Class<? extends Command> clazz : implementationClasses) {
                ClassMeta annotation = clazz.getAnnotation(ClassMeta.class);
                if (annotation != null) {
                    if (annotation.access().equals(args.get(RequestBodyKeys.ARG_OBJ))){
                        response += annotation.name()+"\n";
                    }
                }
            }
            OutManager.push(StatusCodes.OK,response);
        }catch (AccessDeniedException e){
            OutManager.push(StatusCodes.ERROR, "Access denied");
        } catch (CommandNotAcceptArgumentsException e) {
            OutManager.push(StatusCodes.ERROR, "The command ended with an error. Try again.");
        }
    }
}
