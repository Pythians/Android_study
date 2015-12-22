package com.example.wjdev02.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wjdev02.firstandroid.R;

/**
 * Created by wjdev02 on 15/12/22.
 */
public class StorageSQL extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String c = " | ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_sql_layout);
        setTitle(getResources().getString(R.string.storage_sqlite));
        databaseHelper = new DatabaseHelper(this,"BookStore.db",null,1);

        Button buttonCreate = (Button)findViewById(R.id.storage_sql_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.getWritableDatabase();
                query();
            }
        });

        Button buttonAdd = (Button)findViewById(R.id.storage_sql_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.beginTransaction();
                try{
                    ContentValues values = new ContentValues();
                    values.put("name","The Da vinci Code");
                    values.put("author","Dan Brown");
                    values.put("pages",434);
                    values.put("price", 16.92);
                    db.insert("book", null, values);
                    values.clear();

                    values.put("name","The Lost symbol");
                    values.put("author","Dan Brown");
                    values.put("pages",345);
                    values.put("price",19.92);
                    db.insert("book", null, values);
                    values.clear();
                    db.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    db.endTransaction();
                }
                query();
            }
        });

        Button buttonQue = (Button)findViewById(R.id.storage_sql_que);
        buttonQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        Button buttonUpd = (Button)findViewById(R.id.storage_sql_upd);
        buttonUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("book", values, "name = ?", new String[]{"The Da vinci Code"});
                query();
            }
        });

        Button buttonDel = (Button)findViewById(R.id.storage_sql_del);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.delete("book","pages > ?", new String[]{"0"});
                query();
            }
        });
    }

    private void query(){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String text = "";
        Cursor cursor = db.query("book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                text += name + c + author + c + pages + c + price + '\n';
            }while (cursor.moveToNext());
        }
        cursor.close();

        TextView textView = (TextView)findViewById(R.id.storage_sql_textView);
        textView.setText(text);
    }
}
