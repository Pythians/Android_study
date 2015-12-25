package com.example.wjdev02.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Intent Service Thread: " + Thread.currentThread().getId());

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        long trigger = SystemClock.elapsedRealtime() + 2000 ;
        Intent intent1 = new Intent(this,ServiceBroadcastNotice.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pendingIntent);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart");
    }
}
