package com.example.dts2020.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.TestLooperManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dts2020.R;
import com.example.dts2020.model.Data;

import java.util.List;

public class AdapterAlamat extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public AdapterAlamat(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
        }
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.nama);
        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
        Data data = items.get(i);

        id.setText(data.getId());
        name.setText(data.getNama());
        alamat.setText(data.getAlamat());

        return convertView;
    }
}
