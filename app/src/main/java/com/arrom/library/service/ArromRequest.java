package com.arrom.library.service;

import com.arrom.library.http.HttpMethod;

/**
 * Created by Administrator on 2017/2/26.
 */

public class ArromRequest {

    private String mUrl;

    private HttpMethod mMethod;

    private byte[] mData;

    private ArromResponse mResponse;

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String mContentType) {
        this.mContentType = mContentType;
    }

    private String mContentType;


    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public void setMethod(HttpMethod mMethod) {
        this.mMethod = mMethod;
    }

    public byte[] getData() {
        return mData;
    }

    public void setData(byte[] mData) {
        this.mData = mData;
    }

    public ArromResponse getResponse() {
        return mResponse;
    }

    public void setResponse(ArromResponse mResponse) {
        this.mResponse = mResponse;
    }
}
