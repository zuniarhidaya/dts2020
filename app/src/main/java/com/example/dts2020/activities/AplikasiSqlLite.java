package com.example.dts2020.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dts2020.R;
import com.example.dts2020.database.DatabaseHelper;

public class AplikasiSqlLite extends AppCompatActivity implements View.OnClickListener{

    EditText edtNama;
    Button actSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_sql_lite);

        edtNama = (EditText) findViewById(R.id.edtNama);
        actSimpan = (Button) findViewById(R.id.btnSimpan);
        actSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(edtNama.getText().toString().length() > 0){
            SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();

            ContentValues contentvalues = new ContentValues();
            contentvalues.put(DatabaseHelper._COL_NAME,edtNama.getText().toString());

            long insert = database.insert(DatabaseHelper._TB_STUDENT,null,contentvalues);

            if(insert != -1){
                Toast.makeText(this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                edtNama.setText("");
            } else {
                Toast.makeText(this, "Terjadi kesalahan",Toast.LENGTH_SHORT).show();
            }
            database.close();
        } else {
            Toast.makeText(this, "Mohon Masukkan Nama", Toast.LENGTH_SHORT).show();
        }

    }
}