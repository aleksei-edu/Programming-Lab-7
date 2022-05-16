package com.lapin.network.conop;

public interface ConnectionType {
    /* all methods must return status of operation
     true - OK
     false - error
     */
    public boolean openSocket();
    public boolean connect();
    public boolean run();

}
