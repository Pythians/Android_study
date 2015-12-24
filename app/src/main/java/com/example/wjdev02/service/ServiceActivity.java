package com.example.wjdev02.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wjdev02.firstandroid.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart ;
    private Button buttonStop ;
    private Button buttonBind ;
    private Button buttonUnbind;

    private MyService.BindWorker worker;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            worker = (MyService.BindWorker) service;
            worker.startWork();
            worker.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevice);
        setTitle("Service");

        buttonStart = (Button)findViewById(R.id.service_start);
        buttonStop = (Button)findViewById(R.id.service_stop);
        buttonBind = (Button)findViewById(R.id.service_bind);
        buttonUnbind = (Button)findViewById(R.id.service_unbind);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnbind.setOnClickListener(this);
    }

    /***********************************************************************************************
    * 服务必需先开启才能绑定
    * 服务停止会解除绑定
    ***********************************************************************************************/
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MyService.class);
        switch (v.getId()){
            case R.id.service_start:
                startService(intent);
                break;
            case R.id.service_stop:
                stopService(intent);
                break;
            case R.id.service_bind:
                bindService(intent,connection,BIND_ABOVE_CLIENT);
                break;
            case R.id.service_unbind:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
