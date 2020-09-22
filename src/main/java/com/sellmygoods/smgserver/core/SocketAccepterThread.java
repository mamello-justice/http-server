package com.sellmygoods.smgserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketAccepterThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(SocketAccepterThread.class);

    private int port;
    private String rootDir;
    private ServerSocket serverSocket;

    public SocketAccepterThread(int port, String rootDir) throws IOException {
        this.port = port;
        this.rootDir = rootDir;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOG.info(" ✔️ Connection accepted: {}", socket.getInetAddress());

                SocketConnectorThread client = new SocketConnectorThread(socket);
                client.start();
            }
        } catch (IOException e) {
            LOG.error(" ✗ Problem setting socket", e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ignored) { }
            }
        }
    }
}
