package com.davisonego.davisonegoa1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCidade extends AppCompatActivity {

    String nom;
    Integer aci;
    Integer vei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cidade);

        EditText txtNom = findViewById(R.id.txtNom);
        EditText txtVei = findViewById(R.id.txtVei);
        EditText txtAci = findViewById(R.id.txtAci);
        Button btnSbmt = findViewById(R.id.btnSbmt);

        btnSbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = txtNom.getText().toString();
                aci = Integer.parseInt(txtAci.getText().toString());
                vei = Integer.parseInt(txtVei.getText().toString());
                new InsertCity().execute();
                finish();
            }
        });
    }

    class InsertCity extends AsyncTask<String, Void, String> {
        DBAdapter conexaoDB = new DBAdapter(AddCidade.this);

        @Override
        protected String doInBackground(String... strings) {
            try {
                conexaoDB.open();
                conexaoDB.insereCidade(nom,vei,aci);
                conexaoDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}