package com.example.zpo_lab10;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ActivityAddToDataBase extends Activity {
    SQLiteDatabase database;
    int[] columnIndices = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = openOrCreateDatabase("TENISISCI", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS TENISISCI (Id INTEGER, Imie VARCHAR, Nazwisko VARCHAR, DataUrodzenia VARCHAR)";
        database.execSQL(sqlDB);
        columnIndices[0] = getIntent().getIntExtra("ID_COL", 0);
        columnIndices[1] = getIntent().getIntExtra("SURNAME_COL", 0);
        columnIndices[2] = getIntent().getIntExtra("NAME_COL", 0);
        columnIndices[3] = getIntent().getIntExtra("DATE_COL", 0);
        Log.d("loadint", String.valueOf(columnIndices[1]));
    }

    public void btnAddPersonClicked(View view) {
        EditText et1 = (EditText) findViewById(R.id.inputID);
        EditText et2 = (EditText) findViewById(R.id.inputName);
        EditText et3 = (EditText) findViewById(R.id.inputLastName);
        EditText et4 = (EditText) findViewById(R.id.inputYearOfBirth);
        int id = Integer.valueOf(et1.getText().toString());
        String imie = et2.getText().toString();
        String nazwisko = et3.getText().toString();
        String dataUrodzenia = et4.getText().toString();
        String sqlTenisist = "INSERT INTO TENISISCI VALUES (?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sqlTenisist);
        statement.bindLong(columnIndices[0], (long) id);
        statement.bindString(columnIndices[1], imie);
        statement.bindString(columnIndices[2], nazwisko);
        statement.bindString(columnIndices[3], dataUrodzenia);
        statement.executeInsert();
    }
}