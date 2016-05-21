package com.example.marek.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marek on 2016-05-21.
 */
public class Databases_Manager extends SQLiteOpenHelper {
    public Databases_Manager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table restaurant(" +
                        "nr integer primary key autoincrement," +
                        "nazwa text," +
                        "adress text," +
                        "tak integer," +
                        "nie integer);" +
                        "");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add_restaurant(String nazwa, String adress, int tak, int nie){ //add security about error when data was't add to databases
        SQLiteDatabase databases = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nazwa", nazwa);
        values.put("adress", adress);
        values.put("tak",tak);
        values.put("nie", nie);
        databases.insertOrThrow("restaurant", null, values);

    }

    public Cursor read_all_kolumns(){
        String[] kolumns= {"nr","nazwa", "adress","tak", "nie"};
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("restaurant", kolumns,null,null,null,null,null);
        return cursor;
    }
}
