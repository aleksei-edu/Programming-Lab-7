package com.lapin.network.obj;

import java.util.HashMap;

public interface NetObjects {

    public NetObjHeaderKeys getHeader();
    public HashMap<NetObjBodyKeys,Object> getBody();

}
