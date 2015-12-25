package com.example.wjdev02.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.example.wjdev02.firstandroid.R;
import com.example.wjdev02.notification.NotificationActivity;

/**
 * Created by wjdev02 on 15/12/25.
 */
public class ServiceBroadcastNotice extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context, NotificationActivity.class);
        intent.putExtra("notice",true);
        intent.putExtra("content", "service->broadcast->notice");
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager manager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.gold_coin_single);
        builder.setTicker("Intent Service");
        builder.setContentText("service->broadcast->notice");
        builder.setContentTitle("sbn");
        builder.setContentInfo("info");
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.getNotification();
        manager.notify(1,notification);
    }
}
