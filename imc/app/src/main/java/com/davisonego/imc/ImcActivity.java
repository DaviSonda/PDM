package com.davisonego.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ImcActivity extends AppCompatActivity {

    TextView lblImc;

    TextView lblTextImc;
    float valor = 0;
    float peso = 0;
    float altura = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        lblImc = (TextView) findViewById(R.id.lblImc);
        lblTextImc = (TextView) findViewById(R.id.lblTextImc);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            peso = Float.parseFloat(extras.getString("peso"));
            altura = Float.parseFloat(extras.getString("altura"));
            System.out.println(peso);
            System.out.println(altura);
            valor = (float) peso / (altura * altura);
            System.out.println(valor);

            lblImc.setText("IMC: " + String.format("%.2f", valor));
            String txtImc;
            if(valor < 18.5) {
                txtImc = getResources().getString(R.string.imc1);
            } else if(valor < 25) {
                txtImc = getResources().getString(R.string.imc2);
            } else if(valor < 30) {
                txtImc = getResources().getString(R.string.imc3);
            } else if(valor < 35) {
                txtImc = getResources().getString(R.string.imc4);
            } else if(valor < 40) {
                txtImc = getResources().getString(R.string.imc5);
            } else {
                txtImc = getResources().getString(R.string.imc6);
            }
            lblTextImc.setText(txtImc);
        }
    }
}