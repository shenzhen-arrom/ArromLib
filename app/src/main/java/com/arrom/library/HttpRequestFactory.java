package com.arrom.library;

import com.arrom.library.http.HttpMethod;
import com.arrom.library.http.HttpRequest;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface HttpRequestFactory {

    HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException;

}
