package com.lapin.network.obj;

import java.io.Serializable;
import java.util.HashMap;

/**
 * It's abstract impl of Requestable interface
 */
public class NetObj implements Serializable, NetObjects {
    private String header;
    private HashMap<String,Object> body;
    public NetObj(){

    }
    public NetObj(String header, HashMap<String,Object> body){
        this.header = header;
        this.body = body;
    }
    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public HashMap<String, Object> getBody() {
        return body;
    }
}
