package com.example.marek.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by marek on 2016-05-21.
 */
public class Databases_Manager extends SQLiteOpenHelper {
    public String table_name;
    String[] kolumns= {"nr","nazwa", "adress","tak", "nie"};
    public Databases_Manager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String table_name) {
        super(context, name, factory, version);
        this.table_name = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table" +table_name+"(" +
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
        databases.insertOrThrow(table_name, null, values);

    }

    public Cursor read_all_kolumns(){
        String[] kolumns= {"nr","nazwa", "adress","tak", "nie"};
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(table_name, kolumns,null,null,null,null,null);
        return cursor;
    }

    public void delete_row(int id){
        SQLiteDatabase database = getWritableDatabase();
        String[] arguments = {"" + id};
        database.delete(table_name,"nr=?",arguments);

    }

    public void delete_allRow(){
        SQLiteDatabase databases = getReadableDatabase();
        String[] kolumns = {"MAX(nr) as nr"};
        Cursor cursor = databases.query(table_name, kolumns, null, null, null, null, null);
        int size = 0;
        while(cursor.moveToNext()) {
            size = cursor.getInt(0);
            Log.d("dane bazy",Integer.toString(size));
        }


      SQLiteDatabase database = getWritableDatabase();
        for(long i= 0;i<size+1;i++) {
            String[] arguments = {"" + i};
            database.delete(table_name, "nr=?",arguments);
        }
        
        database.delete("sqlite_sequence",null,null);
      cursor.close();
    }
}
