package com.arrom.library.okhttp;

import com.arrom.library.AbstractHttpResponse;
import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpStates;

import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/26.
 */

public class OkHttpResponse extends AbstractHttpResponse {

    private Response mResponse;

    private HttpHeader mHeader;


    public OkHttpResponse(Response response){
       this.mResponse=response;
    }

    @Override
    protected void closeInternal() {
        mResponse.body().close();
    }

    @Override
    protected InputStream getBodyInternal() {
        return mResponse.body().byteStream();
    }

    @Override
    public HttpStates getStatus() {
        return HttpStates.getValue(mResponse.code());
    }

    @Override
    public String getStatusMsg() {
        return mResponse.message();
    }

    @Override
    public long getContentLength() {
        return mResponse.body().contentLength();
    }

    @Override
    public HttpHeader getHeaders() {
       if(mHeader==null){
           mHeader=new HttpHeader();
       }
        for (String name : mResponse.headers().names()) {
            mHeader.set(name,mResponse.headers().get(name));
        }
        return mHeader;
    }
}
