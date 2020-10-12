package com.example.dts2020.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String _DBNAME = "dbStudent";
    public static int _VERSION = 1;

    public static String _TB_STUDENT = "tb_student";
    public static String _COL_ID = "id";
    public static String _COL_NAME = "fullname";

    public DatabaseHelper(@Nullable Context context) {
        super(context, _DBNAME, null, _VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ _TB_STUDENT + "(" +_COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+_COL_NAME+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
