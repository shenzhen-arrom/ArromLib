package com.arrom.library.service.convert;

import com.arrom.library.http.HttpResponse;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface Convert {

    Object parse(HttpResponse response,Type type) throws IOException;
    Object parse(String response,Type type) throws IOException;

    boolean isCanParse(String contentType);

}
