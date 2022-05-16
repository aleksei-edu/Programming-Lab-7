package com.lapin.common.interaction;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String commandArg;
    private Serializable commandObjArg;

    public Request(String commandName, String commandArg, Serializable commandObjArg){
        this.commandName = commandName;
        this.commandArg = commandArg;
        this.commandObjArg = commandObjArg;
    }
    public Request(String commandName, String commandArg){
        this(commandName,commandArg,null);
    }

    public String getCommandName(){
        return commandName;
    }

    public String getCommandArg() {
        return commandArg;
    }

    public Serializable getCommandObjArg() {
        return commandObjArg;
    }
}
