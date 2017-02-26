package com.arrom.library.origin;

import com.arrom.library.HttpRequestFactory;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * Created by Administrator on 2017/2/26.
 * 原生的
 */

public class OriginHttpRequestFactory implements HttpRequestFactory {

    private HttpURLConnection mConnection;

    public OriginHttpRequestFactory(){

    }
    public void setReadTimeOut(int readTimeOut){
        mConnection.setReadTimeout(readTimeOut);
    }
    public void setConnectTimeout(int connectTimeout){
        mConnection.setConnectTimeout(connectTimeout);
    }
    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException{
        try {
            mConnection= (HttpURLConnection) uri.toURL().openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OriginHttpRequest(mConnection,method,uri.toString());
    }
}
