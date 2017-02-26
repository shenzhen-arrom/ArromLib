package com.arrom.library.service.convert;

import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/2/26.
 */

public class JsonConvert implements Convert{

    private Gson gson=new Gson();
    @Override
    public Object parse(HttpResponse response, Type type) throws IOException {
        Reader reader=new InputStreamReader(response.getBody());
        return  gson.fromJson(reader,type);
    }

    @Override
    public Object parse(String response, Type type) throws IOException {
        return  gson.fromJson(response,type);
    }

    @Override
    public boolean isCanParse(String contentType) {
        return Contants.CONTENT_TYPE.equals(contentType);
    }
}
