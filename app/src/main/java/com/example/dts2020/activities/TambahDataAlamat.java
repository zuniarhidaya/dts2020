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
import com.example.dts2020.database.DatabaseAlamat;

public class TambahDataAlamat extends AppCompatActivity implements View.OnClickListener{

    EditText edtNama, edtAlamat;
    Button btnSubmit,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_alamat);

        edtAlamat = findViewById(R.id.edtAlamat);
        edtNama = findViewById(R.id.edtNama);
        btnCancel = findViewById(R.id.actionCancel);
        btnSubmit = findViewById(R.id.action_tambah);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.actionSubmit:
                if(edtNama.getText().toString().length()>0 && edtAlamat.getText().toString().length()>0){

                } else {
                    Toast.makeText(this, "Mohon lnegkapi data",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.actionCancel:
                finish();
                break;
        }
    }

//    void simpanData(){
//        SQLiteDatabase database = new DatabaseAlamat(this).getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("nama",edtNama.getText().toString());
//        contentValues.put("alamat",edtAlamat.getText().toString());
//
//        long insert = database.insert("tb_alamat",null,contentValues);
//
//        if(insert == -1){
//
//        }
//        database.close();
//    }
}