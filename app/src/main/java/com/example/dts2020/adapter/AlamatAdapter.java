package com.example.dts2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dts2020.R;
import com.example.dts2020.model.ModelAlamat;

public class AlamatAdapter extends ArrayAdapter<ModelAlamat> {

    int resource;
    Context context;

    public AlamatAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            holder.txtNama = convertView.findViewById(R.id.txtNama);
            holder.txtAlamat = convertView.findViewById(R.id.txtAdress);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//        holder.setT
        return super.getView(position, convertView, parent);
    }

    class Holder{
        TextView txtNama, txtAlamat;
    }
}
