package com.arrom.library.service;

import com.arrom.library.HttpRequestProvider;
import com.arrom.library.http.Contants;
import com.arrom.library.http.HttpRequest;
import com.arrom.library.http.HttpRunnable;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/2/26.
 */

public class WorkStation {

    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {

        private AtomicInteger index=new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread=new Thread();
            thread.setName("http thread name is "+index.getAndIncrement());
            return thread;
        }
    });
    private Deque<ArromRequest> mRunning=new ArrayDeque<>();
    private Deque<ArromRequest> mCache=new ArrayDeque<>();

    private HttpRequestProvider mProvider;


    public WorkStation(){
        mProvider=new HttpRequestProvider();
    }

    public void add(ArromRequest request){
        if(mRunning.size()> Contants.MAX_REQUEST){
            mCache.add(request);
        }else{
            doHttpRequest(request);
        }
    }

    private void doHttpRequest(ArromRequest request) {
        HttpRequest mHttpRequest=null;
        try {
            mHttpRequest=mProvider.getHttpRequest(URI.create(request.getUrl()),request.getMethod());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sThreadPool.execute(new HttpRunnable(mHttpRequest,request,this));
    }

    public  void finish(ArromRequest request){
        mRunning.remove(request);
        if(mRunning.size()>Contants.MAX_REQUEST){
            return;
        }
        if(mCache.size()==0){
            return;
        }
        Iterator<ArromRequest> iterator=mCache.iterator();
        while (iterator.hasNext()){
            ArromRequest next=iterator.next();
            mRunning.add(next);
            iterator.remove();
            doHttpRequest(next);
        }
    }

}
