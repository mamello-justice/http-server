package com.sellmygoods.smgserver.http;

import com.sellmygoods.smgserver.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class HttpParserTest {

    @BeforeAll
    public static void beforeAll() {
        ConfigurationManager.getInstance().loadHttpValidHeaders("src/main/resources/http-headers.json");
    }

    @Test
    void parseHttpGetRequest() {
        try {
            HttpMessage message = HttpParser.parseHttpRequest(generateValidGetRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseHttpPostRequest() {
        try {
            HttpMessage message = HttpParser.parseHttpRequest(generateValidPostRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream generateValidGetRequest() {
        String stringRequest = "GET /index.html HTTP/1.1\r\n" +
                "Host: localhost:8000\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Connection: Keep-Alive\r\n";

        return new ByteArrayInputStream(
                stringRequest.getBytes(StandardCharsets.US_ASCII)
        );
    }

    private InputStream generateValidPostRequest() {
        String stringRequest = "POST / HTTP/1.1\r\n" +
                "Host: localhost:8000\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n" +
                "Content-Type: text/xml; charset=utf-8\r\n" +
                "Content-Length: 60\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Connection: Keep-Alive\r\n" +
                "\r\n" +
                "first=Zara&last=Ali\r\n";

        return new ByteArrayInputStream(
                stringRequest.getBytes(StandardCharsets.US_ASCII)
        );
    }
}