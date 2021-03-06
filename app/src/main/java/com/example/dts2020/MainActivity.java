package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dts2020.activities.AplikasiSqlLite;
import com.example.dts2020.activities.AplikasiSqlite11;
import com.example.dts2020.activities.AplikasiValidasiLogin;
import com.example.dts2020.activities.SplashScreen;

public class MainActivity extends AppCompatActivity {

    String[] menu = new String[]{"Input Nama", "Aplikasi Kalkulator", "Aplikasi ListView", "Aplikasi Internal Storage",
            "Aplikasi Eksternal Storage", "Proyek 1: Catatan Harian", "Proyek 2: Validasi Login", "Aplikasi SQLite", "Aplikasi Database Sqlite"};

    ListView listView;

    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listMenu);

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
                    pindah = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(pindah);
                } else if (i == 6) {
                    pindah = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(pindah);
                } else if (i == 7) {
                    pindah = new Intent(MainActivity.this, AplikasiSqlLite.class);
                    startActivity(pindah);
                } else if (i == 8) {
                    pindah = new Intent(MainActivity.this, AplikasiSqlite11.class);
                    startActivity(pindah);
                }
            }
        });
    }
}
