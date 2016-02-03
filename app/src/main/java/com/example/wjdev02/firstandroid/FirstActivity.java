package com.example.wjdev02.firstandroid;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wjdev02.Web.FullscreenActivity;
import com.example.wjdev02.contenteresolver.ContentResolverActivity;
import com.example.wjdev02.notification.NotificationActivity;
import com.example.wjdev02.service.ServiceActivity;
import com.example.wjdev02.storage.StorageActivity;

public class FirstActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "OnCreate");
        setContentView(R.layout.activity_first);

        Button startWidgetActivity = (Button) findViewById(R.id.start_widget_activity);
        Button startLayoutActivity = (Button) findViewById(R.id.start_layout_activity);
        Button startBroadcast = (Button) findViewById(R.id.start_broadcast);
        Button startStorage = (Button) findViewById(R.id.start_storage);
        Button startContent = (Button) findViewById(R.id.start_contentResolver);
        Button startNotification = (Button) findViewById(R.id.start_notification);
        Button startService = (Button) findViewById(R.id.start_service);
        Button startApp = (Button) findViewById(R.id.start_app);

        startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this, FullscreenActivity.class);
//                startActivity(intent);
                WebView webView = new WebView(v.getContext());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                webView.loadUrl("http://www.bing.com");
                FrameLayout layout = new FrameLayout(v.getContext());
                layout.addView(webView);
                ImageButton button = new ImageButton(v.getContext());
                button.setImageResource(R.drawable.gold_coin_single);
                button.setX(100);
                button.setY(100);
//                layout.addView(button);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                addContentView(layout,layoutParams);
            }
        });

        startWidgetActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, UIWidgetActivity.class);
                startActivity(intent);
            }
        });

        startLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, UILayoutActivity.class);
                startActivity(intent);
            }
        });

        startBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });

        startStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, StorageActivity.class);
                startActivity(intent);
            }
        });

        startContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ContentResolverActivity.class);
                startActivity(intent);
            }
        });

        startNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
