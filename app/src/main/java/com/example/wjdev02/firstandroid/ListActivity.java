package com.example.wjdev02.firstandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    private String[] data = {"apple","banana","orange","watermelon","pear","grape",
        "pineapple","Strawberry","cherry","mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("ListView");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this,data[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
