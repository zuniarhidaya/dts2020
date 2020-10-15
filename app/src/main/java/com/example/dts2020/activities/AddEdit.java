package com.example.dts2020.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dts2020.R;
import com.example.dts2020.helper.DbHelper;

public class AddEdit extends AppCompatActivity {

    EditText textName, textId, textAddress;
    Button buttonSubmit, buttonCancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        textName = findViewById(R.id.textName);
        textId = findViewById(R.id.textId);
        textAddress = findViewById(R.id.textAddress);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonCancel = findViewById(R.id.buttonCancel);

        id = getIntent().getStringExtra(AplikasiSqlLite.TAG_ID);
        name = getIntent().getStringExtra(AplikasiSqlLite.TAG_NAME);
        address = getIntent().getStringExtra(AplikasiSqlLite.TAG_ADDRESS);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            textId.setText(id);
            textName.setText(name);
            textAddress.setText(address);
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (textId.getText().toString().equals("")) {
save();
                    } else {
edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
blank();
finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
blank();
this.finish();
return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank(){
        textName.requestFocus();
        textId.setText(null);
        textName.setText(null);
        textAddress.setText(null);
    }

    private void save(){
        if(String.valueOf(textName.getText()).equals(null) || String.valueOf(textName.getText()).equals("")
            || String.valueOf(textAddress.getText()).equals(null) || String.valueOf(textAddress.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        }else {
            SQLite.insert(textName.getText().toString().trim(), textAddress.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit(){
        if(String.valueOf(textName.getText()).equals(null) || String.valueOf(textName.getText()).equals("")
                || String.valueOf(textAddress.getText()).equals(null) || String.valueOf(textAddress.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        }else {
            SQLite.update(Integer.parseInt(textId.getText().toString().trim()), textName.getText().toString().trim(),
                    textAddress.getText().toString().trim());
            blank();
            finish();
        }
    }
}
