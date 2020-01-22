package com.example.zpo_lab10;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends Activity {
    SQLiteDatabase database;
    int[] columnIndices = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = openOrCreateDatabase("TENISISCI", MODE_PRIVATE, null);

        String sqlDB = "CREATE TABLE IF NOT EXISTS TENISISCI (Id INTEGER, Imie VARCHAR, Nazwisko VARCHAR, DataUrodzenia VARCHAR)";
        database.execSQL(sqlDB);

    }

    public void btnShowClicked(View view) {
        ArrayList<String> wyniki = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT Id, Imie, Nazwisko, DataUrodzenia From TENISISCI", null);
        if (c.moveToFirst()) {
            columnIndices[0] = c.getColumnIndex("Id") + 1;
            columnIndices[1] = c.getColumnIndex("Imie") + 1;
            columnIndices[2] = c.getColumnIndex("Nazwisko") + 1;
            columnIndices[3] = c.getColumnIndex("DataUrodzenia") + 1;
            do {
                int id = c.getInt(c.getColumnIndex("Id"));
                String imie = c.getString(c.getColumnIndex("Imie"));
                String nazwisko =
                        c.getString(c.getColumnIndex("Nazwisko"));
                String dataUrodzenia =
                        c.getString(c.getColumnIndex("DataUrodzenia"));
                wyniki.add(id + ". " + imie + ", " + nazwisko + ", " + dataUrodzenia);
            } while (c.moveToNext());
        }
        ListView listView = (ListView) findViewById(R.id.listDataBase);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, wyniki);
        listView.setAdapter(adapter);
        c.close();
    }

    public void btnAddClicked(View view) {
        Intent intent = new Intent(this, ActivityAddToDataBase.class);
        intent.putExtra("ID_COL", columnIndices[0]);
        intent.putExtra("SURNAME_COL", columnIndices[1]);
        intent.putExtra("NAME_COL", columnIndices[2]);
        intent.putExtra("DATE_COL", columnIndices[3]);
        this.startActivity(intent);
    }

    public void btnDeleteClicked(View view) {
        Intent intent = new Intent(this, ActivityDeleteFromBase.class);
        intent.putExtra("ID_COL", columnIndices[0]);
        intent.putExtra("SURNAME_COL", columnIndices[1]);
        intent.putExtra("NAME_COL", columnIndices[2]);
        intent.putExtra("DATE_COL", columnIndices[3]);
        this.startActivity(intent);
    }

    public void btnAgeClicked(View view) {
        Intent intent = new Intent(this, ActivityShowAge.class);
        intent.putExtra("ID_COL", columnIndices[0]);
        intent.putExtra("SURNAME_COL", columnIndices[1]);
        intent.putExtra("NAME_COL", columnIndices[2]);
        intent.putExtra("DATE_COL", columnIndices[3]);
        this.startActivity(intent);
    }
}

