package com.arrom.library.service;

import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpMethod;
import com.arrom.library.service.convert.Convert;
import com.arrom.library.service.convert.JsonConvert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/2/26.
 */

public class ArromApiProvider {

    private static WorkStation mWorkStation=new WorkStation();

    private static  final List<Convert> sConvert=new ArrayList<>();

    static {
        sConvert.add(new JsonConvert());
    }

    public static byte[] encodeParam(Map<String,String> value){
        if(value==null||value.size()==0){
            return null;
        }
        StringBuffer buffer=new StringBuffer();
        int count=0;
        try{
            for (Map.Entry<String, String> entry : value.entrySet()) {
                buffer.append(URLEncoder.encode(entry.getKey(), Contants.ENCODING))
                        .append("=").append(URLEncoder.encode(entry.getValue(),Contants.ENCODING));
                if(count!=value.size()-1){
                    buffer.append("&");
                }
                count++;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return buffer.toString().getBytes();
    }

    public static void httpPostApi(String url, Map<String,String> map,ArromResponse response){
        ArromRequest request=new ArromRequest();
        WrapperResponse wrapperResponse=new WrapperResponse(response,sConvert);
        request.setUrl(url);
        request.setMethod(HttpMethod.POST);
        request.setData(encodeParam(map));
        request.setResponse(wrapperResponse);
        mWorkStation.add(request);
    }


}
