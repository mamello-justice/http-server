package com.sellmygoods.smgserver.http;

import java.util.List;

public class HttpHeader {
    private final String key;
    private final String value;

    public static List<HttpHeader> validHeaders;

    public HttpHeader() {
        key = "";
        value = "";
    }

    public HttpHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", key, value);
    }
}
