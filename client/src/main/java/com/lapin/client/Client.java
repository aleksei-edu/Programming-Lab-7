package com.lapin.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

public class Client {
    public static final Logger logger
            = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        Session session = new Session();
    }
}
