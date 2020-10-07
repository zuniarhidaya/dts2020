package com.example.dts2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AplikasiListView extends AppCompatActivity {

    private ListView lview;
    private String[] namanegara = new String[]{"Indonesia","Malaysia","Singapura","Thailand","Arab Saudi", "Palestina",
    "Afganistan","Mesir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_list_view);

        getSupportActionBar().setTitle("Listview Sederhana"); //judul

        lview = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AplikasiListView.this, android.R.layout.simple_list_item_1, android.R.id.text1, namanegara);

        lview.setAdapter(adapter);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AplikasiListView.this, "Memilih : "+namanegara[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}