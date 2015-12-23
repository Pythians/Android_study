package com.example.wjdev02.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wjdev02 on 15/12/22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book ("
            +"id integer primary key autoincrement, "
            +"author text, "
            +"price real, "
            +"pages integer, "
            +"name text, "
            +"category_id integer)";

    public static  final String CREATE_CATEGORY = "create table Category("
            +"id integer primary key autoincrement, "
            +"category_name text, "
            +"category_code integer) ";

    private Context mContext;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
//        Toast.makeText(mContext,"Create success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
                break;
            case 2:
                db.execSQL("alter table book add column category_id integer ");
                break;
            default:
                break;
        }
    }
}
