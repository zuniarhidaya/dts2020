package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dts2020.activities.AplikasiCatatanHarian;
import com.example.dts2020.activities.AplikasiEksternalStorage;
import com.example.dts2020.activities.AplikasiSqlLite;
import com.example.dts2020.activities.AplikasiValidasiLogin;
import com.example.dts2020.activities.AplikasiInternalStorage;

public class MainActivity extends AppCompatActivity {

    String[] menu = new String[]{"Input Nama","Aplikasi Kalkulator","Aplikasi ListView", "Aplikasi Internal Storage",
    "Aplikasi Eksternal Storage", "Proyek 1: Catatan Harian", "Proyek 2: Validasi Login", "Aplikasi SQLite"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listMenu);

        ArrayAdapter arrayAdapter =new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                menu);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = (String) parent.getAdapter().getItem(i);

                Intent pindah = null;

                if(i==0){
                    pindah =new Intent(MainActivity.this,AplikasiNama.class);
                    startActivity(pindah);
                } else if(i==1){
                    pindah =new Intent(MainActivity.this,AplikasiKalkulator.class);
                    startActivity(pindah);
                } else if (i==2){
                    pindah =new Intent(MainActivity.this,AplikasiListView.class);
                    startActivity(pindah);
                } else if (i==3){
                    pindah =new Intent(MainActivity.this, AplikasiInternalStorage.class);
                    startActivity(pindah);
                } else if (i==4){
                    pindah =new Intent(MainActivity.this, AplikasiEksternalStorage.class);
                    startActivity(pindah);
                } else if (i==5){
                    pindah =new Intent(MainActivity.this, AplikasiCatatanHarian.class);
                    startActivity(pindah);
                } else if (i==6){
                    pindah =new Intent(MainActivity.this, AplikasiValidasiLogin.class);
                    startActivity(pindah);
                } else if(i==7){
                    pindah =new Intent(MainActivity.this, AplikasiSqlLite.class);
                    startActivity(pindah);
                }
            }
        });
    }
}