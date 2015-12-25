package com.example.wjdev02.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.example.wjdev02.firstandroid.R;
import com.example.wjdev02.notification.NotificationActivity;

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
        Log.d(TAG, "onCreate");

        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("service title");
        builder.setContentText("service content");
        startForeground(1, builder.getNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");

//        Log.d(TAG, "Service Thread: " + Thread.currentThread().getId());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run at Thread: " + Thread.currentThread().getId());
//                stopSelf();
//            }
//        }).start();

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
