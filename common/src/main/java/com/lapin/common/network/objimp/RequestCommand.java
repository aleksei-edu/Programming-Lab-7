package com.lapin.common.network.objimp;

import com.lapin.network.obj.NetObj;

import java.io.Serializable;
import java.util.HashMap;

public class RequestCommand extends NetObj implements Serializable {
    public RequestCommand(HashMap<String,Object> body){
        super("request", body);
    }
    public RequestCommand(String command, String arg, Object argObj){
        super("request", setBody(command,arg,argObj));
    }
    private static HashMap<String,Object> setBody(String command, String arg, Object argObj){
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", command);
        body.put("arg", arg);
        body.put("argObj", argObj);
        return body;
    }
    public String getCmd(){
        return (String) super.getBody().get("command");
    }
    public String getArg(){
        return (String) super.getBody().get("arg");
    }
    public String argObj(){
        return (String) super.getBody().get("argObj");
    }
}
