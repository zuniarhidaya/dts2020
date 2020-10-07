package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AplikasiNama extends AppCompatActivity {

    String name;
    EditText edtName;
    Button btnName;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_nama);

        edtName = (EditText) findViewById(R.id.edtInputNama);
        btnName = (Button) findViewById(R.id.btnTampilkan);
        txtName = (TextView) findViewById(R.id.txtNama);

        name = String.valueOf(edtName.getText());

        btnName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txtName.setText(name);
            }
        });
    }
}