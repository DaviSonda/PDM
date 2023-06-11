package com.davisonego.petshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PetAdapter extends ArrayAdapter<PetDisplay> {
    private Context mContext;
    private int mResource;

    private String perm;
    private String id;

    public PetAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PetDisplay> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.perm = getPermission(context);
    }

    public String getPermission(Context context) {
        return new FileHelper().ReadFile(context, "login.txt");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        PetDisplay pet = getItem(position);

        convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView txtTit = convertView.findViewById(R.id.txtTit);

        TextView txtDes = convertView.findViewById(R.id.txtDesc);

        TextView txtCont = convertView.findViewById(R.id.txtCont);

        ImageButton btnDelete = convertView.findViewById(R.id.btnDelete);

        btnDelete.setBackgroundColor(Color.TRANSPARENT);

        if (perm.contains("ADMIN")) {
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = pet.getId();
                new Delete().execute();
            }
        });

        String imgB64 = pet.getImg();

        imageView.setImageBitmap(new Base64Helper().decodeBase64(imgB64));

        txtTit.setText(pet.getTit());

        txtDes.setText(pet.getDes());

        txtCont.setText(pet.getCont());

        return convertView;
    }

    class Delete extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                URL url = new URL(Globals.url + "/posts/" + id);

                // create HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("DELETE");

                // read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }
}
