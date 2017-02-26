package com.arrom.library;

import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class AbstractHttpResponse implements HttpResponse {


    private InputStream mGzipInputStream;

    @Override
    public void close() throws IOException {
        if(mGzipInputStream!=null){
            mGzipInputStream.close();
        }
        closeInternal();
    }
    protected  abstract void closeInternal();
    @Override
    public InputStream getBody() throws IOException {
        InputStream body = getBodyInternal();
        if (isGzip()) {
            return getBodyGzip(body);
        }
        return body;
    }

    private InputStream getBodyGzip(InputStream body) throws IOException {
        if (this.mGzipInputStream == null) {
            this.mGzipInputStream = new GZIPInputStream(body);
        }
        return mGzipInputStream;
    }

    private boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();
        if (Contants.GZIP.equals(contentEncoding)) {
            return true;
        }
        return false;
    }


    protected abstract InputStream getBodyInternal() throws IOException;
}
