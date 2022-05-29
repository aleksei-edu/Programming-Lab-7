package com.lapin.network.listener;

import com.lapin.network.ClientType;
import com.lapin.network.StatusCodes;
import com.lapin.network.config.NetworkConfigurator;
import com.lapin.network.log.NetworkLogger;
import com.lapin.network.obj.ClientRequestHandler;
import com.lapin.network.obj.NetObj;
import com.lapin.network.obj.Serializer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ClientListener implements Listenerable{
    private NetworkConfigurator config;
    private Reader reader;
    private ClientType clientType;
    private StatusCodes clientStatus;
    private NetworkLogger netLogger;
    private SocketChannel socketChannel;
    public ClientListener(NetworkConfigurator config, SocketChannel socketChannel){
        this(config,new InputStreamReader(System.in),socketChannel);
    }
    public ClientListener(NetworkConfigurator config, Reader reader, SocketChannel socketChannel){
        this.config = config;
        this.reader = reader;
        this.socketChannel = socketChannel;
        this.netLogger = config.getNetLogger();
        this.clientType = config.getClientType();
    }
    @Override
    public void startUp() {
        while(!clientStatus.equals(StatusCodes.EXIT_CLIENT)){
            try(BufferedReader in = new Buffered-Reader(reader)) {
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                if (!"".equals(input)) {
                    NetObj request = config.getClientRequestHandler().handle(input,config);
                    if (request == null){
                        continue;
                    }
                    else {
                        write(request);
                        NetObj response = read();

                    }
                }

                } catch (IOException e) {
                netLogger.error("Error on start-up!");
            }
        }
    }
    private void write(NetObj request) throws IOException {
        byte[] bytes = Serializer.serialize(request);
        getOutputStream().write(bytes);
    }
    private NetObj read() throws IOException {
        ByteBuffer mainBuffer = ByteBuffer.allocate(0);
        InputStream inputStream = getInputStream();
        while (true) {
            byte[] bytesToDeserialize = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            int bytesCount = bis.read(bytesToDeserialize);
            ByteBuffer newBuffer = ByteBuffer.allocate(mainBuffer.capacity() + bytesCount);
            newBuffer.put(mainBuffer);
            newBuffer.put(ByteBuffer.wrap(bytesToDeserialize, 0, bytesCount));
            mainBuffer = ByteBuffer.wrap(newBuffer.array());
            NetObj response = (NetObj) Serializer.deserialize(mainBuffer.array());
            if (response == null) {
                List<ByteBuffer> buffers = new ArrayList<>();
                int bytesLeft = bis.available();
                int len = bytesLeft;
                while (bytesLeft > 0) {
                    byte[] leftBytesToSerialize = new byte[bytesLeft];
                    bis.read(leftBytesToSerialize);
                    buffers.add(ByteBuffer.wrap(leftBytesToSerialize));
                    bytesLeft = bis.available();
                    len += bytesLeft;
                }
                newBuffer = ByteBuffer.allocate(len + mainBuffer.capacity());
                newBuffer.put(mainBuffer);
                buffers.forEach(newBuffer::put);
                mainBuffer = ByteBuffer.wrap(newBuffer.array());
                response = (NetObj) Serializer.deserialize(mainBuffer.array());
            }
            if (response != null) {
                return response;
            }
        }
    }
    private OutputStream getOutputStream(){
        try {
            return socketChannel.socket().getOutputStream();
        } catch (IOException e) {
            netLogger.error("Failed to open OutputStream");
        }
        return null;
    }
    private InputStream getInputStream(){
        try {
            return socketChannel.socket().getInputStream();
        } catch (IOException e) {
            netLogger.error("Failed to open InputStream");
        }
        return null;
    }
    private void setClientStatus(StatusCodes clientStatus){
        this.clientStatus = clientStatus;
    }
}
