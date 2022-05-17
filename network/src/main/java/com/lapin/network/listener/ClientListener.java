package com.lapin.network.listener;

import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.log.NetworkLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ClientListener implements Listenerable{
    private NetworkConfigurator config;
    private Reader reader;
    private ClientType clientType;
    private StatusCodes clientStatus;
    private NetworkLogger netLogger;
    public ClientListener(NetworkConfigurator config){
        this(config,new InputStreamReader(System.in));
    }
    public ClientListener(NetworkConfigurator config, Reader reader){
        this.config = config;
        this.reader = reader;
        this.netLogger = config.getNetLogger();
        this.clientType = config.getClientType();
    }
    @Override
    public void startUp() {
        while(!clientStatus.equals(StatusCodes.EXIT_CLIENT)){
            try(BufferedReader in = new BufferedReader(reader)) {
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                if (!"".equals(input)) {

                }

                } catch (IOException e) {
                netLogger.error("Error on start-up!");
            }
        }
    }
    private void write(){

    }
}
