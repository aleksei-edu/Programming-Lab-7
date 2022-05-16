package com.lapin.common.network.objimp;

import com.lapin.network.obj.NetObj;

import java.io.Serializable;
import java.util.HashMap;

public class ResponceCommand extends NetObj implements Serializable {
    public ResponceCommand(String ans){
        super("responce", setBody(ans));
    }
    private static HashMap<String,Object> setBody(String ans){
        HashMap<String, Object> body = new HashMap<>();
        body.put("ans", ans);
        return body;
    }
}

