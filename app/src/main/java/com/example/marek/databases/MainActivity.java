package com.example.marek.databases;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public static final String DBNAME = "test.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        Databases_Manager databases_manager = new Databases_Manager(this,DBNAME,null,1);
        databases_manager.add_restaurant("Kolos Kebab","Zwierzyniecka 8",123,12);
        databases_manager.add_restaurant("KFC","Mickiewicza 8",133,342);
        databases_manager.add_restaurant("Burger King","Leśna 9",123,132);
        Cursor cursor = databases_manager.read_all_kolumns();
        while(cursor.moveToNext()){
            int nr = cursor.getInt(0);
            String name = cursor.getString(1);
            String adress = cursor.getString(2);
            int tak = cursor.getInt(3);
            int nie = cursor.getInt(4);
            textView.setText(textView.getText()+ "\n" + nr + " " + name + " " + adress + " " + tak + " " + nie);
        }

    }
}
