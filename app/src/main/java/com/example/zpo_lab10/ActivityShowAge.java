package com.example.zpo_lab10;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActivityShowAge extends Activity {
    SQLiteDatabase database;
    int[] columnIndices = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        database = openOrCreateDatabase("TENISISCI", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS TENISISCI (Id INTEGER, Imie VARCHAR, Nazwisko VARCHAR, DataUrodzenia VARCHAR)";
        database.execSQL(sqlDB);
        columnIndices[0] = getIntent().getIntExtra("ID_COL", 0);
        columnIndices[1] = getIntent().getIntExtra("SURNAME_COL", 0);
        columnIndices[2] = getIntent().getIntExtra("NAME_COL", 0);
        columnIndices[3] = getIntent().getIntExtra("DATE_COL", 0);
        Log.d("loadint", String.valueOf(columnIndices[1]));
    }

    public void btnCalculateClicked(View view) {
        final TextView count = (TextView) findViewById(R.id.calculatedValue);
        ArrayList<String> results = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT Id, Imie, Nazwisko, DataUrodzenia From TENISISCI", null);
        EditText et1 = (EditText) findViewById(R.id.inputID2);
        int podanyWiek = Integer.valueOf(et1.getText().toString());
        int counter = 0;

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
                String[] data = dataUrodzenia.split("-");
                String rok = data[2];
                int year = Integer.parseInt(rok);
                LocalDate now = LocalDate.now();
                int yearNow = now.getYear();
                int wiek = yearNow - year;
                if (podanyWiek == wiek) {
                    counter++;
                    results.add(id + ". " + imie + ", " + nazwisko + ", "
                            + dataUrodzenia);
                }
            } while (c.moveToNext());
        }
        ListView listView = (ListView) findViewById(R.id.listCalculated);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, results);
        listView.setAdapter(adapter);
        count.setText(Integer.toString(counter));
        c.close();
    }

}
