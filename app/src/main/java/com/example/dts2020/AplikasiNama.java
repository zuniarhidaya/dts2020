package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AplikasiNama extends AppCompatActivity {

    EditText editNama;
    Button actionTampilkan;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_nama);

        editNama = (EditText) findViewById(R.id.editNama);
        actionTampilkan = (Button) findViewById(R.id.buttonTampil);
        hasil = (TextView) findViewById(R.id.hasil);

        actionTampilkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasil.setText(editNama.getText().toString());
            }
        });
    }
}