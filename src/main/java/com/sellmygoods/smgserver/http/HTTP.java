package com.sellmygoods.smgserver.http;

public enum HTTP {
    SP(" "), CRLF("\r\n");

    private final String s;
    HTTP(String s) { this.s = s; }
    String getValue() { return s; }

    public enum METHOD {
        GET, HEAD, POST, PUT, DELETE
    }

    public enum VERSION {
        HTTP_0_9("HTTP/0.9"), HTTP_1_0("HTTP/1.0"), HTTP_1_1("HTTP/1.1"), HTTP_2_0("HTTP/2.0");

        private final String v;

        VERSION(String v) {
            this.v = v;
        }

        String getValue() {
            return v;
        }

        @Override
        public String toString() {
            return v;
        }

        static VERSION getEnum(String value) {
            for (VERSION v : values()) {
                if (v.v.equals(value)) return v;
            }
            throw new IllegalArgumentException();
        }
    }
}