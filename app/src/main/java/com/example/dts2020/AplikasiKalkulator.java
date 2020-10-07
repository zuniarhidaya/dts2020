package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AplikasiKalkulator extends AppCompatActivity {

    float angkaA;
    float angkaB;
    float angkaHasil;
    String hasil;

    Button plus, minus, kali, bagi, bersihkan;
    EditText pertama, kedua;
    TextView txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_kalkulator);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        kali = (Button) findViewById(R.id.kali);
        bagi = (Button) findViewById(R.id.bagi);
        bersihkan = (Button) findViewById(R.id.btnClear);

        pertama = (EditText) findViewById(R.id.numA);
        kedua = (EditText) findViewById(R.id.numB);

        txtHasil = (TextView) findViewById(R.id.txtHasil);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angkaA = Float.parseFloat(String.valueOf(pertama.getText()));
                angkaB = Float.parseFloat(String.valueOf(kedua.getText()));

                angkaHasil = angkaA + angkaB;

                hasil = String.valueOf(angkaHasil);

                txtHasil.setText(hasil);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angkaA = Float.parseFloat(String.valueOf(pertama.getText()));
                angkaB = Float.parseFloat(String.valueOf(kedua.getText()));

                angkaHasil = angkaA - angkaB;

                hasil = String.valueOf(angkaHasil);

                txtHasil.setText(hasil);
            }
        });
        kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angkaA = Float.parseFloat(String.valueOf(pertama.getText()));
                angkaB = Float.parseFloat(String.valueOf(kedua.getText()));

                angkaHasil = angkaA * angkaB;

                hasil = String.valueOf(angkaHasil);

                txtHasil.setText(hasil);
            }
        });
        bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angkaA = Float.parseFloat(String.valueOf(pertama.getText()));
                angkaB = Float.parseFloat(String.valueOf(kedua.getText()));

                angkaHasil = angkaA / angkaB;

                hasil = String.valueOf(angkaHasil);

                txtHasil.setText(hasil);
            }
        });

        bersihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pertama.setText("");
                kedua.setText("");
                txtHasil.setText("");
            }
        });
    }
}