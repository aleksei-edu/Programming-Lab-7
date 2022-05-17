package com.lapin.network.obj;

import java.io.Serializable;
import java.util.HashMap;

/**
 * It's abstract impl of Requestable interface
 */
public class NetObj implements Serializable, NetObjects {
    private NetObjHeaderKeys header;
    private HashMap<NetObjBodyKeys,Object> body;
    public NetObj(){

    }
    public NetObj(NetObjHeaderKeys header, HashMap<NetObjBodyKeys,Object> body){
        this.header = header;
        this.body = body;
    }
    @Override
    public NetObjHeaderKeys getHeader() {
        return header;
    }

    @Override
    public HashMap<NetObjBodyKeys, Object> getBody() {
        return body;
    }
}
