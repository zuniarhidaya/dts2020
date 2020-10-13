package com.example.dts2020.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.dts2020.R;
import com.example.dts2020.adapter.AlamatAdapter;
import com.example.dts2020.database.DatabaseAlamat;
import com.example.dts2020.model.ModelAlamat;

import java.util.ArrayList;

public class AplikasiDatabase extends AppCompatActivity {

    ListView listView;
    AlamatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi_database);

        listView = findViewById(R.id.listViewdb);
        adapter = new AlamatAdapter(this, R.layout.item_layout_adress);
        listView.setAdapter(adapter);

//        Intent intent = new Intent(AplikasiDatabase.this,TambahDataAlamat.class);
//        startActivity(intent);
    }

//    void getData(){
//        ArrayList<ModelAlamat> datas = new ArrayList<>();
//        adapter.clear();
//        SQLiteDatabase database = new DatabaseAlamat(this).getWritableDatabase();
//        Cursor cursor = database.query("tb_alamat",null,null,null,null,null,null);
//
//        if(cursor.moveToFirst()){
//            do {
//                ModelAlamat item = new ModelAlamat();
//                item.setId(cursor.getInt(0));
//                item.setNama(cursor.getString(1));
//                item.setAlamat(cursor.getString(2));
//                datas.add(item);
//            } while (cursor.moveToNext());
//        }
//
//        adapter.addAll(datas);
//        adapter.notifyDataSetChanged();
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        getData();
    }
}