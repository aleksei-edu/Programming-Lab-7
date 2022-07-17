package com.lapin.common.utility;

import com.lapin.common.data.User;

import java.io.Serializable;

public interface Client_IO {
    public Pair handle(String command, String arg, Serializable argObj, User user);
    public Pair handle(String command, String arg, User user);
}
