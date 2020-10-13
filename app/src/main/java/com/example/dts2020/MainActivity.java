package com.example.dts2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] menu = new String[]{"Input Nama", "Aplikasi Kalkulator", "Aplikasi ListView", "Penyimpanan Internal Storage", "Penyimpanan External Storage", "Login"};

    ListView listView;
    EditText editUsername, editPassword, editEmail, editNama, editAsal, editAlamat;
    Button simpan;
    TextView password;

    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listMenu);
        editAlamat = findViewById(R.id.editAlamat);
        editAsal = findViewById(R.id.editAsal);
        editEmail = findViewById(R.id.editEmail);
        editNama = findViewById(R.id.editNama);
        editPassword = findViewById(R.id.editPassword);
        editUsername = findViewById(R.id.editUsername);
        //simpan = findViewById(R.id.btnSimpan);

//        simpan.setVisibility(View.GONE);
//        editUsername.setEnabled(false);
//        editPassword.setVisibility(View.GONE);
//        password.setVisibility(View.GONE);
//        editAlamat.setEnabled(false);
//        editNama.setEnabled(false);
//        editEmail.setEnabled(false);
//        editAsal.setEnabled(false);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                menu);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = (String) parent.getAdapter().getItem(i);

                Intent pindah = null;

                if (i == 0) {
                    pindah = new Intent(MainActivity.this, AplikasiNama.class);
                    startActivity(pindah);
                } else if (i == 1) {
                    pindah = new Intent(MainActivity.this, AplikasiKalkulator.class);
                    startActivity(pindah);
                } else if (i == 2) {
                    pindah = new Intent(MainActivity.this, AplikasiListView.class);
                    startActivity(pindah);
                } else if (i == 3) {
                    pindah = new Intent(MainActivity.this, InternalActivity.class);
                    startActivity(pindah);
                } else if (i == 4) {
                    pindah = new Intent(MainActivity.this, EksternalActivity.class);
                    startActivity(pindah);
                } else if (i == 5) {
                    pindah = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(pindah);
                }
            }
        });
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
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
