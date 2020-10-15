package com.example.dts2020.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dts2020.R;
import com.example.dts2020.adapter.Adapter;
import com.example.dts2020.helper.DbHelper;
import com.example.dts2020.model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AplikasiSqlLite extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemlist = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_sql_lite);

        SQLite = new DbHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.listViewSql);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AplikasiSqlLite.this, AddEdit.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(AplikasiSqlLite.this, itemlist);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemlist.get(position).getId();
                final String name = itemlist.get(position).getName();
                final String address = itemlist.get(position).getAddress();

                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(AplikasiSqlLite.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(AplikasiSqlLite.this, AddEdit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemlist.clear();
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
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i <row.size() ; i++) {
            String id = row.get(i).get(TAG_ID);
            String name = row.get(i).get(TAG_NAME);
            String address = row.get(i).get(TAG_ADDRESS);

            Data data = new Data();

            data.setId(id);
            data.setName(name);
            data.setAddress(address);

            itemlist.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        itemlist.clear();
        getAllData();
    }

}