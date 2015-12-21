package com.example.wjdev02.firstandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadcastActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

//    private BootCompleteReceiver bootCompleteReceiver;
    private IntentFilter intentFilter;

    // 本地通知
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        setTitle("Broadcast");

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        bootCompleteReceiver = new BootCompleteReceiver();
//        registerReceiver(bootCompleteReceiver,intentFilter);

        //  获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Button sent = (Button) findViewById(R.id.button_broadcast);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("com.example.broadcasttest.MY");
//                sendBroadcast(intent);
                // 发送本地通知
                Intent intent = new Intent("com.example.broadcasttest.LOCAL");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL");
        // 注册本地通知
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册本地通知
        localBroadcastManager.unregisterReceiver(localReceiver);
//        unregisterReceiver(bootCompleteReceiver);
    }


    // 本地通知接收回调
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"Received local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
