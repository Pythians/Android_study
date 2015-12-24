package com.example.wjdev02.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }


    private static final String TAG = "MyService";
    private BindWorker binder = new BindWorker();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return binder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    class BindWorker extends Binder{
        public void startWork(){
            Log.d(TAG,"start work");
        }

        public int getProgress(){
            Log.d(TAG, "get Progress");
            return 0;
        }
    }
}
