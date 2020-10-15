package com.example.dts2020.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

import com.example.dts2020.MainActivity;
import com.example.dts2020.R;
import com.example.dts2020.adapter.AdapterAlamat;
import com.example.dts2020.helper.DbHelper;
import com.example.dts2020.model.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AplikasiSqlite11 extends AppCompatActivity {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "alamat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_sqlite11);

        SQLite = new DbHelper(getApplicationContext());
        Button tambah = findViewById(R.id.btnTambahAlamat);
        listView = (ListView) findViewById(R.id.listAlamat);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AplikasiSqlite11.this, AddEdit.class);
                startActivity(intent);
            }
        });
        adapter = new AdapterAlamat(AplikasiSqlite11.this, itemList);
        listView.setAdapter((ListAdapter) adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String idx = itemList.get(i).getId();
                final String nama = itemList.get(i).getNama();
                final String alamat = itemList.get(i).getAlamat();

                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(AplikasiSqlite11.this);
                dialog.setCancelable(true);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(AplikasiSqlite11.this, AddEdit.class);
                                intent.putExtra(TAG_NAME, idx);
                                intent.putExtra(TAG_NAME, nama);
                                intent.putExtra(TAG_ADDRESS, alamat);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }

    private void getAllData(){
        ArrayList<HashMap<String,String>> row = SQLite.getAllData();
        for(int i=0;i<row.size();i++){
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_ADDRESS);
            Data data = new Data();

            data.setId(id);
            data.setNama(poster);
            data.setAlamat(title);
            itemList.add(data);
        }
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}