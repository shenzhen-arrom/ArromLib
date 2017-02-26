package com.arrom.library.origin;

import com.arrom.library.AbstractHttpResponse;
import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpStates;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 * 原声的http
 */

public class OriginHttpResponse extends AbstractHttpResponse {

    private HttpURLConnection mConnection;

    public  OriginHttpResponse(HttpURLConnection connection){
        this.mConnection=connection;
    }
    @Override
    public HttpStates getStatus() {
        try {
            return HttpStates.getValue(mConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getStatusMsg() {
        try {
            return mConnection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getContentLength() {
        return mConnection.getContentLength();
    }

    @Override
    protected InputStream getBodyInternal() throws IOException {
        return mConnection.getInputStream();
    }
    @Override
    protected void closeInternal() {
        mConnection.disconnect();
    }

    @Override
    public HttpHeader getHeaders() {
        HttpHeader header=new HttpHeader();
        for (Map.Entry<String, List<String>> entry : mConnection.getHeaderFields().entrySet()) {
            header.set(entry.getKey(),entry.getValue().get(0));
        }
        return header;
    }
}
