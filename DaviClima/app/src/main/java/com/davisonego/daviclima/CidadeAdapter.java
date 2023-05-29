package com.davisonego.daviclima;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CidadeAdapter extends ArrayAdapter<Cidade> {
    private Context mContext;
    private int mResource;

    public CidadeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Cidade> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        Cidade pet = getItem(position);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView txtTit = convertView.findViewById(R.id.txtNom);

        TextView txtDes = convertView.findViewById(R.id.txtUF);

        TextView txtCont = convertView.findViewById(R.id.txtCod);

        ImageButton btn = convertView.findViewById(R.id.btnAdd);

        btn.setBackgroundColor(Color.TRANSPARENT);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aqui vai ter add de item b
                System.out.println("Cavlo");
            }
        });

        txtTit.setText(pet.getNome());

        txtDes.setText(pet.getUF());

        txtCont.setText(pet.getCodigo());

        return convertView;
    }
}
