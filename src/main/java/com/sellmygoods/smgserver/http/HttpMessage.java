package com.sellmygoods.smgserver.http;

import java.util.ArrayList;
import java.util.List;

public class HttpMessage {
    private HttpStartLine startLine;
    private List<HttpHeader> headers;
    private HttpMessageBody body;

    public HttpMessage() {
        this.startLine = new HttpStartLine();
        this.headers = new ArrayList<>();
        this.body = new HttpMessageBody();
    }

    public HttpMessage startLine(HttpStartLine startLine) {
        this.startLine = startLine;
        return this;
    }

    public HttpMessage headers(List<HttpHeader> headers) {
        this.headers = headers;
        return this;
    }

    public HttpMessage messageBody(HttpMessageBody body) {
        this.body = body;
        return this;
    }

    public static HttpMessage generate() {
        return new HttpMessage();
    }

    public HttpStartLine getStartLine() {
        return startLine;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public HttpMessageBody getMessageBody() {
        return body;
    }
}
