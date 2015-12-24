package com.example.wjdev02.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wjdev02.firstandroid.R;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notification");

        Button sent = (Button)findViewById(R.id.notice_sent);

        if (getIntent().getBooleanExtra("notice",false)){
            sent.setVisibility(View.GONE);
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.notice_RootLayout);
            TextView view = new TextView(this);
            String content = getIntent().getStringExtra("content");
            content = content == null ? "Get Notification" : content;
            view.setText(content);
            view.setTextSize(32);
            view.setTextColor(Color.GREEN);
            layout.addView(view);
            Toast.makeText(this,"get notification",Toast.LENGTH_SHORT).show();
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(1);
        }else {
            sent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sentNotice();
                }
            });
        }
    }

    private void sentNotice(){
        Intent intent = new Intent(this,getClass());
        intent.putExtra("notice",true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.gold_coin_single);
        builder.setTicker("ticker");
        builder.setContentText("text");
        builder.setContentTitle("title");
        builder.setContentInfo("info");
        builder.setContentIntent(pendingIntent);
        builder.setWhen(System.currentTimeMillis());
        Notification notification = builder.getNotification();
        notification.defaults = Notification.DEFAULT_ALL;
        manager.notify(1,notification);
    }
}
