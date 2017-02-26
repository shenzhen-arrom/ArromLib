package com.arrom.library;

import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpRequest;
import com.arrom.library.okhttp.OkHttpRequestFactory;
import com.arrom.library.origin.OriginHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2017/2/26.
 */

public class HttpRequestProvider {

    private HttpRequestFactory mHttpRequestFactory;

    public HttpRequestFactory getmHttpRequestFactory() {
        return mHttpRequestFactory;
    }

    public void setmHttpRequestFactory(HttpRequestFactory mHttpRequestFactory) {
        this.mHttpRequestFactory = mHttpRequestFactory;
    }

    private static boolean OKHTTP_REQUEST=Utils.isExist(Contants.OKHTTP,HttpRequestProvider.class.getClassLoader());

    public HttpRequestProvider(){
          if(OKHTTP_REQUEST){
              mHttpRequestFactory=new OkHttpRequestFactory();
          }else{
              mHttpRequestFactory=new OriginHttpRequestFactory();
          }
    }
    public HttpRequest getHttpRequest(URI uri, HttpMethod httpMethod) throws IOException{
         return mHttpRequestFactory.createHttpRequest(uri,httpMethod);
    }

}
