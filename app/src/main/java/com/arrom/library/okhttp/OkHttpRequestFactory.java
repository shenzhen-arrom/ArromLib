package com.arrom.library.okhttp;

import com.arrom.library.HttpRequestFactory;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpRequest;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/2/26.
 */

public class OkHttpRequestFactory implements HttpRequestFactory {

    private OkHttpClient mClient;
    public   OkHttpRequestFactory(){
        this.mClient=new OkHttpClient();
    }

    public OkHttpRequestFactory(OkHttpClient client){
        this.mClient=client;
    }

    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method) {
        return new OkHttpRequest(mClient,method,uri.toString());
    }

    public void setReadTimeOut(int readTimeOut){
        this.mClient=mClient.newBuilder().readTimeout(readTimeOut, TimeUnit.NANOSECONDS).build();
    }
    public void setWriteTimeOut(int writeTimeOut){
        this.mClient=mClient.newBuilder().writeTimeout(writeTimeOut,TimeUnit.MILLISECONDS).build();
    }
    public void setConnectionTime(int connectionTime){
        this.mClient=mClient.newBuilder().connectTimeout(connectionTime,TimeUnit.MILLISECONDS).build();
    }



}
