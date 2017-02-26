package com.arrom.library.http;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface NameValueMap<K,V> extends Map<K,V>{

    String get(String key);

    void set(String key,String value);

    void setAll(Map<String,String> map);
}
