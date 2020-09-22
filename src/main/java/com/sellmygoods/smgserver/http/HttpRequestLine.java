package com.sellmygoods.smgserver.http;

import java.net.URI;

public class HttpRequestLine extends HttpStartLine {
    private final HTTP.METHOD method;
    private final URI uri;
    private final HTTP.VERSION version;

    public HttpRequestLine(HTTP.METHOD method, URI uri, HTTP.VERSION version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
    }

    public HTTP.METHOD getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public HTTP.VERSION getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", method, uri, version);
    }
}
