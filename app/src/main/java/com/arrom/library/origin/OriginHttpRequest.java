package com.arrom.library.origin;

import com.arrom.library.BufferHttpRequest;
import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 * 原生的http
 */

public class OriginHttpRequest extends BufferHttpRequest {

    private HttpURLConnection mConnection;
    private String mUrl;
    private HttpMethod mMethod;

    public OriginHttpRequest(HttpURLConnection connection, HttpMethod method, String url) {
        this.mConnection = connection;
        this.mMethod = method;
        this.mUrl = url;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            mConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod(mMethod.name());
        mConnection.connect();
        if(data!=null&&data.length>0){
            OutputStream out=mConnection.getOutputStream();
            out.write(data,0,data.length);
            out.close();
        }
        OriginHttpResponse response=new OriginHttpResponse(mConnection);
        return response;
    }

    @Override
    public HttpMethod getMethod() {
        return mMethod;
    }

    @Override
    public URI getUri() {
        return URI.create(mUrl);
    }
}
