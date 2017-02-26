package com.arrom.library.service;

import com.arrom.library.service.convert.Convert;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/2/26.
 */

public class WrapperResponse extends ArromResponse<String>{

    private  ArromResponse mResponse;

    private List<Convert> mConvert;
    public WrapperResponse(ArromResponse response,List<Convert> converts){
        this.mResponse=response;
        this.mConvert=converts;
    }

    @Override
    public void success(ArromRequest request, String data) {
        for (Convert convert : mConvert) {
            if(convert.isCanParse(request.getContentType())){
                try {
                    Object object=convert.parse(data,getType());
                    mResponse.success(request,object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
    public  Type getType(){
        Type type=mResponse.getClass().getGenericSuperclass();
        Type[] paramType=((ParameterizedType)type).getActualTypeArguments();
        return paramType[0];
    }
    @Override
    public void fail(int errorCode, String errorMsg) {

    }
}
