package com.example.wjdev02.contenteresolver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.wjdev02.storage.DatabaseHelper;

/**
 * Created by wjdev02 on 15/12/23.
 */
public class contentProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    public static final String AUTHORITY = "com.example.wjdev02.firstandroid.provide";

    private DatabaseHelper dbHelper;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",TABLE1_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",TABLE1_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",TABLE2_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext(),"BookStore.db",null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                cursor = db.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE1_ITEM:
                cursor = db.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE2_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE2_ITEM:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.wjdev02.firstandroid.provider.book";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.wjdev02.firstandroid.provider.book";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.wjdev02.firstandroid.provider.Category";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.wjdev02.firstandroid.provider.Category";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
            case TABLE1_ITEM:
                long bid = db.insert("book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+bid);
                break;
            case TABLE2_DIR:
            case TABLE2_ITEM:
                long cid = db.insert("Category",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+cid);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int delete = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                delete = db.delete("book",selection,selectionArgs);
                break;
            case TABLE1_ITEM:
                String bid = uri.getPathSegments().get(1);
                delete = db.delete("book","id = ?",new String[]{bid});
                break;
            case TABLE2_DIR:
                delete = db.delete("Category",selection,selectionArgs);
                break;
            case TABLE2_ITEM:
                String cid = uri.getPathSegments().get(1);
                delete = db.delete("Category","id = ?",new String[]{cid});
                break;
            default:
                break;
        }
        return delete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int update = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                update = db.update("book",values,selection,selectionArgs);
                break;
            case TABLE1_ITEM:
                String bid = uri.getPathSegments().get(1);
                update = db.update("book",values,"id= ?",new String[]{bid});
                break;
            case TABLE2_DIR:
                update = db.update("Category",values,selection,selectionArgs);
                break;
            case TABLE2_ITEM:
                String cid = uri.getPathSegments().get(1);
                update = db.update("Category",values,"id = ?",new String[]{cid});
                break;
            default:
                break;
        }
        return update;
    }
}
