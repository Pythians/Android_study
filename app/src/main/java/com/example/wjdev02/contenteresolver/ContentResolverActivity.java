package com.example.wjdev02.contenteresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wjdev02.firstandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ContentResolverActivity extends AppCompatActivity {

    private String newId;

    private static final String baseUri = "content://com.example.wjdev02.firstandroid.provide/book";
    private static String c = " | ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        setTitle("ContentResolver");

        Button add_b = (Button)findViewById(R.id.add_data);
        add_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(baseUri);
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                query();
            }
        });

        Button que_b = (Button)findViewById(R.id.query_data);
        que_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        Button upd_b = (Button)findViewById(R.id.update_data);
        upd_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(baseUri + '/' + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages",1212);
                values.put("price",24.52);
                getContentResolver().update(uri,values,null,null);
                query();
            }
        });

        Button del_b = (Button)findViewById(R.id.delete_data);
        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(baseUri);// + '/' + newId);
                getContentResolver().delete(uri, null, null);
                query();
            }
        });
    }

    private void query(){
        Uri uri = Uri.parse(baseUri);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String text = "";
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                text += name + c + author + c + pages + c + price + '\n';

            }
            TextView textView = (TextView) findViewById(R.id.show_data);
            textView.setText(text);
            cursor.close();
        }
    }
}
