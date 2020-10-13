package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword, editEmail, editNama, editAsal, editAlamat;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editAlamat = findViewById(R.id.editAlamat);
        editAsal = findViewById(R.id.editAsal);
        editEmail = findViewById(R.id.editEmail);
        editNama = findViewById(R.id.editNama);
        editPassword = findViewById(R.id.editPassword);
        editUsername = findViewById(R.id.editUsername);
        simpan = findViewById(R.id.btnSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidation()){
                    simpanFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Data Anda!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    boolean isValidation() {
        if (editUsername.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") ||
                editEmail.getText().toString().equals("") ||
                editNama.getText().toString().equals("") ||
                editAsal.getText().toString().equals("") ||
                editAlamat.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = editUsername.getText().toString() + ";" +
                editPassword.getText().toString() + ";" +
                editEmail.getText().toString() + ";" +
                editNama.getText().toString() + ";" +
                editAsal.getText().toString() + ";" +
                editAlamat.getText().toString();

        File file = new File(getFilesDir(), editUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}