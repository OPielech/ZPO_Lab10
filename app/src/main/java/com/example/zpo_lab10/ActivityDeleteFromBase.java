package com.example.zpo_lab10;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ActivityDeleteFromBase extends Activity {
    SQLiteDatabase database;
    int[] columnIndices = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        database = openOrCreateDatabase("TENISISCI", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS TENISISCI (Id INTEGER, Imie VARCHAR, Nazwisko VARCHAR, DataUrodzenia VARCHAR)";
        database.execSQL(sqlDB);
        columnIndices[0] = getIntent().getIntExtra("ID_COL", 0);
        columnIndices[1] = getIntent().getIntExtra("SURNAME_COL", 0);
        columnIndices[2] = getIntent().getIntExtra("NAME_COL", 0);
        columnIndices[3] = getIntent().getIntExtra("DATE_COL", 0);
        Log.d("loadint", String.valueOf(columnIndices[1]));
    }

    public void btnDeletePersonClicked(View view) {
        EditText et1 = (EditText) findViewById(R.id.inputID2);
        int id = Integer.valueOf(et1.getText().toString());
        String sqlTenisist = "DELETE FROM TENISISCI WHERE Id=?";
        SQLiteStatement statement = database.compileStatement(sqlTenisist);
        statement.bindLong(columnIndices[0], id);
        statement.executeUpdateDelete();
    }
}