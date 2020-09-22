package com.sellmygoods.smgserver.http;

public class HttpMessageBody {
    private Object data;

    public HttpMessageBody() {
        data = null;
    }

    HttpMessageBody(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return (data==null) ? "None" :
                data.toString();
    }
}
