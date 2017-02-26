package com.arrom.library.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface HttpResponse extends Header,Cloneable {

    HttpStates getStatus();

    String getStatusMsg();

    InputStream getBody() throws IOException;

    void close() throws IOException;

    long getContentLength();
}
