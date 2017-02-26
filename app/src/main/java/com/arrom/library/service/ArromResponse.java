package com.arrom.library.service;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class ArromResponse<T> {
    public abstract void success(ArromRequest request,T data);
    public abstract void fail(int errorCode,String errorMsg);
}
