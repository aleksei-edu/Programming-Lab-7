package com.lapin.common.utility;

import java.io.Serializable;

public interface Client_IO {
    public String handle(String command, String arg, Serializable argObj);
    public String handle(String command, String arg);
}
