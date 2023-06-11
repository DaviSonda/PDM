package com.davisonego.cotacoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaisAdapter extends ArrayAdapter<Pais> {
    private Context mContext;
    private int mResource;

    public PaisAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pais> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        Pais p = getItem(position);

        convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView txtNom = convertView.findViewById(R.id.txtNom);

        TextView txtCot = convertView.findViewById(R.id.txtCot);

        int img = R.drawable.alemanha;
        if (p.getBandeira().equals("alemanha")) {
            img = R.drawable.alemanha;
        } else if (p.getBandeira().equals("eua")) {
            img = R.drawable.eua;
        } else if (p.getBandeira().equals("servia")) {
            img = R.drawable.servia;
        }
        imageView.setImageResource(img);

        txtNom.setText(p.getNome());

        txtCot.setText("Equivale a: " + p.getCotacao() + " reais");

        return convertView;
    }
}
