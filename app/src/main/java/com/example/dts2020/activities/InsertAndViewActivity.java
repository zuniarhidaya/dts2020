package com.example.dts2020.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.dts2020.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InsertAndViewActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_STORAGE = 100;
    int eventID = 0;
    EditText edtFileName, edtCatatan;
    Button btnSimpan;
    boolean isEditable = false;
    String fileName = "";
    String tempCatatan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_and_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edtFileName = findViewById(R.id.edtFilename);
        edtCatatan = findViewById(R.id.edtCatatan);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            fileName = extras.getString("filename");
            edtFileName.setText(fileName);
            getSupportActionBar().setTitle("ubah catatan");
        } else {
            getSupportActionBar().setTitle("Tambah catatan");
        }
        eventID = 1;
        if (Build.VERSION.SDK_INT >=23){
            if(periksaIzinPenyimpanan()){
                bacaFile();
            }
        } else {
            bacaFile();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSimpan:
                eventID = 2;
                if(!tempCatatan.equals(edtCatatan.getText().toString())){
                    if(Build.VERSION.SDK_INT >= 23){
                        if(periksaIzinPenyimpanan()){
                            tampilkanDialogKonfirmasiPenyimpanan();
                        }
                    } else {
                        tampilkanDialogKonfirmasiPenyimpanan();
                    }
                }
                break;
        }
    }

    public boolean periksaIzinPenyimpanan(){
        if(Build.VERSION.SDK_INT >= 23){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_STORAGE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(eventID == 1){
                        bacaFile();
                    } else {
                        tampilkanDialogKonfirmasiPenyimpanan();
                    }
                }
                break;
        }
    }

    void bacaFile() {
        String path = Environment.getExternalStorageDirectory().toString()+"/dts2020.proyek1";
        File file = new File(path, edtFileName,getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader((file)));
            } catch (IOException e) {
                System.out.println("Error"+e.getMessage());
            }
            tempCatatan = text.toString();
            edtCatatan.setText(text.toString());
        }
    }

    void buatDanUbah() {
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }
        String path = Environment.getExternalStorageDirectory().toString()+"/dts2020.proyek1";
        File parent = new File(path);
        if (parent.exists()){
            File file = new File(path, edtFileName.getText().toString());
            FileOutputStream outputStream = null;

            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file);
                streamWriter.append(edtCatatan.getText());
                streamWriter.flush();
                streamWriter.close();
                outputStream.flush();
                outputStream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            parent.mkdir();
            File file = new File(path, edtFileName.getText().toString());
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream =  new FileOutputStream(file,false);
                outputStream.write(edtCatatan.getText().toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        onBackPressed();
    }

    void tampilkanDialogKonfirmasiPenyimpanan() {
        new AlertDialog.Builder(this)
                .setTitle("Simpan Catatan")
                .setMessage("Apakah anda yakin akan menyimpan?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        buatDanUbah();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (!tempCatatan.equals(edtCatatan.getText().toString())){
            tampilkanDialogKonfirmasiPenyimpanan();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}