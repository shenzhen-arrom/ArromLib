package com.arrom.library.http;

import com.arrom.library.service.ArromRequest;
import com.arrom.library.service.WorkStation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/26.
 */

public class HttpRunnable implements Runnable {

    private HttpRequest mHttpRequest;
    private ArromRequest mRequest;
    private WorkStation mWorkStation;

    public HttpRunnable(HttpRequest httpRequest, ArromRequest request,WorkStation workStation) {
        this.mHttpRequest = httpRequest;
        this.mRequest = request;
        this.mWorkStation=workStation;
    }

    @Override

    public void run() {
        try {
            mHttpRequest.getBody().write(mRequest.getData());
            HttpResponse response=mHttpRequest.execute();
            String contentType=response.getHeaders().getContentType();
            mRequest.setContentType(contentType);
            if(response.getStatus().isSuccess()){
                if(mRequest.getResponse()!=null){
                     mRequest.getResponse()
                             .success(mRequest,new String(getData(response)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mWorkStation.finish(mRequest);
        }
    }
    public  byte[] getData(HttpResponse response){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream((int)response.getContentLength());
        int len;
        byte[] data=new byte[512];
        try {
            while ((len=response.getBody().read(data))!=-1){
                 outputStream.write(data,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
