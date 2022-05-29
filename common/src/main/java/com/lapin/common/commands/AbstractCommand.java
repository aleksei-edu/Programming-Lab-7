package com.lapin.common.commands;


import com.lapin.common.network.objimp.RequestBodyKeys;
import com.lapin.di.annotation.ClassMeta;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Абстрактный класс, от которого наследуются все команды.
 */

public class AbstractCommand implements Command {
    private String name;
    private AccessType accessType;
    @Override
    public void execute(String arg, Serializable argObj) {

    }
    @Override
    public String toString() {
        ClassMeta commandMeta = this.getClass().getAnnotation(ClassMeta.class);
        return commandMeta.name() + " – " + commandMeta.description();
    }

    @Override
    public String getName() {
        return this.getClass().getAnnotation(ClassMeta.class).name();
    }

    @Override
    public AccessType getAccessType() {
        return accessType;
    }
    protected void setAccessType(AccessType accessType){
        this.accessType =accessType;
    }
}
