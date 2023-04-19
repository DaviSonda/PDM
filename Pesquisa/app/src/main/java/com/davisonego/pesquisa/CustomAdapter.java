package com.davisonego.pesquisa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<Pessoa> data;

    public CustomAdapter(List<Pessoa> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Pessoa getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        }

        TextView top1 = convertView.findViewById(R.id.top1);
        TextView top2 = convertView.findViewById(R.id.top2);
        TextView bot1 = convertView.findViewById(R.id.bot1);
        TextView bot2 = convertView.findViewById(R.id.bot2);

        Pessoa currentItem = getItem(position);

        top1.setText("Idade: " + currentItem.getIdade().toString());
        top2.setText("Sexo: " + currentItem.getSexo().toString());
        bot1.setText("Cabelos: " + currentItem.getCorCabelo().toString());
        bot2.setText("Olhos: " + currentItem.getCorOlhos().toString());

        return convertView;
    }
}
