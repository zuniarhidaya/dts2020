package com.example.dts2020.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dts2020.R;
import com.example.dts2020.helper.DbHelper;

import java.util.Objects;

public class AddEdit extends AppCompatActivity {
    EditText txt_id,txt_nama,txt_alamat;
    Button btn_submit,btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, nama, alamat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_add_edit);

        txt_id = (EditText) findViewById(R.id.edtId);
        txt_nama = (EditText) findViewById(R.id.edtNamaSql);
        txt_alamat = (EditText) findViewById(R.id.edtAlamatSql);
        btn_submit = (Button) findViewById(R.id.btnSubmit);
        btn_cancel = (Button) findViewById(R.id.btnclean);

        id = getIntent().getStringExtra(AplikasiSqlite11.TAG_ID);
        nama = getIntent().getStringExtra(AplikasiSqlite11.TAG_NAME);
        alamat = getIntent().getStringExtra(AplikasiSqlite11.TAG_ADDRESS);

        if(id==null || id==""){
            setTitle("Add Data");
        }else{
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_nama.setText(nama);
            txt_alamat.setText(alamat);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank(){
        txt_nama.requestFocus();
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_alamat.setText(null);
    }

    private void save(){
        if (String.valueOf(txt_nama.getText()).equals(null) || String.valueOf(txt_nama.getText()).equals("")
        || String.valueOf(txt_alamat.getText()).equals(null) || String.valueOf(txt_alamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"masukkan nama atau alamat",Toast.LENGTH_SHORT);
        } else {
            SQLite.insert(txt_nama.getText().toString().trim(), txt_alamat.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(txt_nama.getText()).equals(null) || String.valueOf(txt_nama.getText()).equals("")
                || String.valueOf(txt_alamat.getText()).equals(null) || String.valueOf(txt_alamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"masukkan nama atau alamat",Toast.LENGTH_SHORT);
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_nama.getText().toString().trim(), txt_alamat.getText().toString().trim());
            blank();
            finish();
        }
    }
}
