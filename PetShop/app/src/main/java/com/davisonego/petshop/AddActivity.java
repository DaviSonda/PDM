package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddActivity extends AppCompatActivity {

    String tipo; //1 = Perdido, 2 = Achado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            tipo = extras.getString("tipo").toString();
        }
    }
}