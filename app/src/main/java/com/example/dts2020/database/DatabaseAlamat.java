package com.example.dts2020.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAlamat extends SQLiteOpenHelper {
    public static String _NAMA_DB = "db_alamat";
    public static int _VERSION = 1;

    public DatabaseAlamat(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tb_alamat (id INTEGER PRIMARY KEY AUTOINCREMENT,nama TEXT, alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
