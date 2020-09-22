package com.sellmygoods.smgserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpParser {

    private static final Logger LOG = LoggerFactory.getLogger(HttpParser.class);

    /**
     * Parse http requests from input stream
     *
     * @param iStream input stream from socket
     * @return HttpMessage object
     * @throws IOException when iStream is not bound
     */
    public static HttpMessage parseHttpRequest(InputStream iStream) throws IOException {
        HttpStartLine startLine = new HttpStartLine();
        List<HttpHeader> headers = new ArrayList<>();
        HttpMessageBody messageBody = new HttpMessageBody();
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();

        int _byte;

        // Parse StartLine
        boolean _method = true;
        String httpMethod = "", httpURI = "", httpVersion = "";
        LOG.info("Parsing start-line...");
        while((_byte = iStream.read()) != -1) {
            if (HTTP.SP.getValue().equals(String.valueOf((char)_byte))) {
                if (_method) httpMethod = oStream.toString().toUpperCase().trim();
                else httpURI = oStream.toString().trim();
                _method = false;
                oStream.reset();
            } else if (oStream.toString().contains(HTTP.CRLF.getValue())) {
                httpVersion = oStream.toString().trim(); // Use value
                oStream.reset();
                break;
            }
            oStream.write(_byte);
        }

        try {
            startLine = new HttpRequestLine(
                    HTTP.METHOD.valueOf(httpMethod),
                    URI.create(httpURI),
                    HTTP.VERSION.getEnum(httpVersion)
            );
            LOG.info(startLine.toString());
        } catch (IllegalArgumentException e) {
            LOG.error(" ✗ Problem processing request-line", e);
        }

        LOG.info("Parsing headers...");

        oStream.write(_byte);
        boolean _firstColon = true;
        String key = "";
        while((_byte = iStream.read()) != -1) {
            if ((char)_byte==':' && _firstColon) {
                key = oStream.toString().trim();
                oStream.reset();    // Read key
                _firstColon = false;
                continue;
            } else if (oStream.toString().equals(HTTP.CRLF.getValue())) {   // End of headers
                oStream.reset();
                break;
            } else if (oStream.toString().contains(HTTP.CRLF.getValue())) { // End header line
                HttpHeader header = new HttpHeader(key, oStream.toString().trim());
                headers.add(header);
                LOG.info(header.toString());
                _firstColon = true;
                oStream.reset();    // Read value
            }
            oStream.write(_byte);
        }

        LOG.info("Parsing message-body...");

        if (_byte != -1) oStream.write(_byte);  // Message body can be none
        while((_byte = iStream.read()) != -1) { oStream.write(_byte); }
        messageBody.setData(oStream.toString());
        LOG.info(messageBody.toString());

        oStream.flush();
        oStream.close();
        return HttpMessage
                .generate()
                .startLine(startLine)
                .headers(headers)
                .messageBody(messageBody);
    }
}
