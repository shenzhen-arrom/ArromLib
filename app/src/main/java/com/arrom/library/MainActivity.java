package com.arrom.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arrom.library.service.ArromApiProvider;
import com.arrom.library.service.ArromRequest;
import com.arrom.library.service.ArromResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String ,String> map=new HashMap<>();
        ArromApiProvider.httpPostApi("", map, new ArromResponse<Person>() {

            @Override
            public void success(ArromRequest request, Person data) {

            }

            @Override
            public void fail(int errorCode, String errorMsg) {

            }
        });
    }
}
