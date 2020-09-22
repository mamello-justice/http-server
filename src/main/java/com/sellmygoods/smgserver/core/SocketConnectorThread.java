package com.sellmygoods.smgserver.core;

import com.sellmygoods.smgserver.http.HttpMessage;
import com.sellmygoods.smgserver.http.HttpParser;
import com.sellmygoods.smgserver.http.HttpRequestLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnectorThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(SocketConnectorThread.class);

    private final Socket socket;
    private final InputStream iStream;
    private final OutputStream oStream;

    public SocketConnectorThread(Socket socket) throws IOException {
        this.socket = socket;
        this.iStream = socket.getInputStream();
        this.oStream = socket.getOutputStream();
        LOG.info(" ✔️ Created socket streams: {}", socket.getInetAddress());
    }

    @Override
    public void run() {
        try {
            HttpMessage httpRequest = HttpParser.parseHttpRequest(iStream);
            switch (((HttpRequestLine)httpRequest.getStartLine()).getMethod()) {
                case GET:
                    System.out.println("Requesting resource");
                    break;

                case HEAD:
                    System.out.println("Requesting resource meta-information");
                    break;

                case PUT:
                    System.out.println("Create or update resource");
                    break;

                case POST:
                    System.out.println("Create resource if not exist");
                    break;

                case DELETE:
                    System.out.println("Remove resource");
                    break;

                default:
            }
        } catch (IOException e) {
            LOG.error("Problem communicating", e);
        } finally {
            if (iStream != null) {
                try {
                    iStream.close();
                } catch (IOException ignored) { }
            }
            if (oStream != null) {
                try {
                    oStream.close();
                } catch (IOException ignored) { }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) { }
            }
        }
    }
}
