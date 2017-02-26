package com.arrom.library.okhttp;

import com.arrom.library.BufferHttpRequest;
import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/26.
 */

public class OkHttpRequest extends BufferHttpRequest {

    private OkHttpClient mClient;
    private HttpMethod mMethod;
    private String mUrl;

    public OkHttpRequest(OkHttpClient client, HttpMethod method, String url){
        this.mClient=client;
        this.mMethod=method;
        this.mUrl=url;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        boolean isBody=mMethod==HttpMethod.POST;
        RequestBody requestBody = null;
        if(isBody){
            requestBody=RequestBody.create(MediaType.parse(Contants.URLENCODED),data);
        }
        Request.Builder builder=new Request.Builder().url(mUrl).method(mMethod.name(),requestBody);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        Response response=mClient.newCall(builder.build()).execute();
        return new OkHttpResponse(response);
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
