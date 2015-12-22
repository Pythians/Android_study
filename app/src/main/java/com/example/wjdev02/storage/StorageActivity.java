package com.example.wjdev02.storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wjdev02.firstandroid.R;

public class StorageActivity extends AppCompatActivity {

    public static final String TITLE_S = "storage_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        setTitle("Storage view");

        Button storage_file = (Button)findViewById(R.id.storage_file);
        storage_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageActivity.this,StorageFile.class);
                intent.putExtra(TITLE_S,R.string.storage_file);
                startActivity(intent);
            }
        });

        Button storage_shareP = (Button)findViewById(R.id.storage_sp);
        storage_shareP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageActivity.this,StorageShare.class);
                intent.putExtra(TITLE_S,R.string.storage_sharedPreference);
                startActivity(intent);
            }
        });

        Button storage_sql = (Button)findViewById(R.id.storage_sql);
        storage_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageActivity.this,StorageSQL.class);
                intent.putExtra(TITLE_S,R.string.storage_sqlite);
                startActivity(intent);
            }
        });
    }
}
