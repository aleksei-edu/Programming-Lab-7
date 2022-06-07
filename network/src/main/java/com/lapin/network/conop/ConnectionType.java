package com.lapin.network.conop;

import com.lapin.network.listener.Listenerable;

import java.nio.channels.spi.AbstractSelectableChannel;

public interface ConnectionType {
    /* all methods must return status of operation
     true - OK
     false - error
     */
    public void openSocket();
    public Listenerable connect();

}
