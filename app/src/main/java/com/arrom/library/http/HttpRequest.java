package com.arrom.library.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface HttpRequest extends Header{
    HttpMethod getMethod();

    URI getUri();

    OutputStream getBody();

    HttpResponse execute() throws IOException;
}
