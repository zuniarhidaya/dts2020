package com.example.dts2020.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "digitalent.db";
    public static final String TABLE_SQLite = "sqlite";
    public static final String COLUMN_NAME = "nama";
    public static final String COLUMN_ADRESS = "alamat";
    public static final String COLUMN_ID = "id";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE "+TABLE_SQLite+" ("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT NOT NULL, "+
                COLUMN_ADRESS+" TEXT NOT NULL"+" )";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_SQLite);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String,String>> getAllData(){
        ArrayList<HashMap<String,String>> worldList;
        worldList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM "+TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<String ,String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_ADRESS, cursor.getString(2));
                worldList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlites",""+worldList);
        database.close();
        return worldList;
    }

    public void insert(String nama, String alamat){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO "+TABLE_SQLite+" (nama, alamat) "+
                " VALUES ('"+ nama + "', '"+ alamat +"')'";
        Log.e("insert sqlite",""+queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String nama, String alamat){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE "+TABLE_SQLite+" SET "+
                COLUMN_NAME+" = '"+nama+"', "+
                COLUMN_ADRESS+" = "+alamat+"'"+
                " WHERE "+COLUMN_ID+" = "+"'"+id+"'";
        Log.e("update sqlite",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM "+TABLE_SQLite+" WHERE "+COLUMN_ID+
                " = "+"'"+id+"'";
        Log.e("update sqlite",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
