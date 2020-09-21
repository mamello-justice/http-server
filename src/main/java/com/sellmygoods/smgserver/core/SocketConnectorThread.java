package com.sellmygoods.smgserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnectorThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(SocketConnectorThread.class);

    private Socket socket;
    private InputStream iStream;
    private OutputStream oStream;

    public SocketConnectorThread(Socket socket) throws IOException {
        this.socket = socket;
        this.iStream = socket.getInputStream();
        this.oStream = socket.getOutputStream();
        LOG.info(" ✔️ Created socket streams: {}", socket.getInetAddress());
    }

    @Override
    public void run() {

    }
}
