package com.example.dts2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    public static final String FILENAME="login";

    EditText editUsername, editPassword, editEmail, editNama, editAsal, editAlamat;
    Button simpan;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editAlamat = findViewById(R.id.editAlamat);
        editAsal = findViewById(R.id.editAsal);
        editEmail = findViewById(R.id.editEmail);
        editNama = findViewById(R.id.editNama);
        editPassword = findViewById(R.id.editPassword);
        editUsername = findViewById(R.id.editUsername);
        simpan = findViewById(R.id.btnSimpan);

        simpan.setVisibility(View.GONE);
        editUsername.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        editPassword.setVisibility(View.GONE);
        editAlamat.setEnabled(false);
        editNama.setEnabled(false);
        editEmail.setEnabled(false);
        editAsal.setEnabled(false);

        bacaFileLogin();
    }


    void bacaFileLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] datauser = data.split(";");
            bacaDataUser(datauser[0]);
        }
    }

    void bacaDataUser(String fileName) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, fileName);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] datauser = data.split(";");

            editUsername.setText(datauser[0]);
            editEmail.setText(datauser[2]);
            editNama.setText(datauser[3]);
            editAsal.setText(datauser[4]);
            editAlamat.setText(datauser[5]);
        } else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
            tampilkanDialogKonfirmasiLogout();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
