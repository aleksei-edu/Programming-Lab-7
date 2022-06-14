package com.lapin.client;

import com.lapin.common.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class RemoteClient {
    public static final Logger logger
            = LoggerFactory.getLogger(RemoteClient.class);

    public static void main(String[] args) {
        File configPath = new File("client/src/main/resources/config.properties");
        Client user = new Client(configPath);
        user.run();
    }
}
