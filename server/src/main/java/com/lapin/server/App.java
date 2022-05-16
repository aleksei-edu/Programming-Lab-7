package com.lapin.server;


import com.lapin.network.TCPConnection;
import com.lapin.server.config.NetworkConfigFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final int PORT = 8000;
    private static final int SOTIMEOUT = 60 * 1000;
    public static final Logger logger
            = LoggerFactory.getLogger(App.class);
    public static void main(String[] args){
        TCPConnection server = new TCPConnection(new NetworkConfigFile());
        server.run();
    }
}
