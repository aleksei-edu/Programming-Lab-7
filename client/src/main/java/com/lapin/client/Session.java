package com.lapin.client;

import com.lapin.network.config.NetworkConfigurator;
import com.lapin.common.interaction.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Session {
    private NetworkConfigurator networkConfigurator = new NetworkConfigFile();
    private InetAddress host;
    private final Integer port = networkConfigurator.getPort();
    private int reconnectionTimeout;
    private final int maxReconnectionAttempts = networkConfigurator.getMaxReconnectionAttemps();
    private Socket clientSocket;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private int reconnectionAttempts;
    public Session() {
        try {
            this.host = networkConfigurator.getHost();
        } catch (UnknownHostException e) {
            System.err.println("Не удалось получить хост");
        }
        this.reconnectionTimeout = networkConfigurator.getTimeout();
        this.run();
    }


    public void run(){
        try {
            boolean processingStatus = true;
            int reconnectionAttempts = 0;
            while (processingStatus) {
                try {
                    reconnectionAttempts++;
                    connectToServer();
                }
                catch (IOException e){
                    Client.logger.error("Произошла ошибка при соединении с сервером!");
                    if(reconnectionAttempts >= (maxReconnectionAttempts-1)){
                        Client.logger.error("Превышено количество попыток подключения!");
                        processingStatus = false;
                    }
                    try {
                        Client.logger.info((reconnectionAttempts+1) +" попытка подключения будет выполнена через "
                        + (reconnectionTimeout/1000) + " секунд.");
                        Thread.sleep(reconnectionTimeout);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    continue;
                }
                processingStatus = processRequestToServer();

            }
            if (clientSocket != null) clientSocket.close();
        }
        catch (IOException e){
            Client.logger.error("Произошла ошибка при попытке завершить соединение с сервером!");
        }
    }
    private void connectToServer() throws IOException{
        if (reconnectionAttempts >= 1)  Client.logger.info("Повторное соединение с сервером...");
        Socket clientSocket = new Socket(host, port);
        serverWriter = new ObjectOutputStream(clientSocket.getOutputStream());
        serverReader = new ObjectInputStream(clientSocket.getInputStream());

    }

    private boolean processRequestToServer(){
        Request request = null;
        String response = null;
        do{
            request = UserHandler.handle();
            if(request == null){continue;}
            try {
                serverWriter.writeObject(request);
                response = (String) serverReader.readObject();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (!request.getCommandName().equals("exit"));
        return false;
    }
}
