package com.arrom.library;

import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpHeader;
import com.arrom.library.http.HttpRequest;
import com.arrom.library.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class AbstractHttpRequest implements HttpRequest {

    private HttpHeader mheader=new HttpHeader();

    private ZipOutputStream mZip;

    private boolean executed=false;

    @Override
    public OutputStream getBody() {
        OutputStream body=getBodyOutputStream();
        if(isGzip()){
           return getGZipOutputSteream(body);
        }
        return body;
    }

    @Override
    public HttpResponse execute() throws IOException {
        if(mZip!=null){
            mZip.close();
        }
        HttpResponse response=executeInternal(mheader);
        executed=true;
        return response;
    }

    protected abstract HttpResponse executeInternal(HttpHeader mheader) throws IOException;

    private OutputStream getGZipOutputSteream(OutputStream body) {
        if(this.mZip==null){
            this.mZip=new ZipOutputStream(body);
        }
        return mZip;
    }
    private boolean isGzip() {
        String contentEncoding =getHeaders().getContentEncoding();
        if(Contants.GZIP.equals(contentEncoding)){
            return true;
        }
        return false;
    }

    protected abstract OutputStream getBodyOutputStream();

    @Override
    public HttpHeader getHeaders() {
        return null;
    }
}
