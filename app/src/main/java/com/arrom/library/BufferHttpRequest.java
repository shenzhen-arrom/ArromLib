package com.arrom.library;

import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class BufferHttpRequest extends AbstractHttpRequest {

    private ByteArrayOutputStream mByteArrayOutputStream=new ByteArrayOutputStream();

    @Override
    protected HttpResponse executeInternal(HttpHeader header) throws IOException {
        byte[] data=mByteArrayOutputStream.toByteArray();
        return executeInternal(header,data);
    }

    protected abstract HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException;

    @Override
    protected OutputStream getBodyOutputStream() {
        return mByteArrayOutputStream;
    }
}
