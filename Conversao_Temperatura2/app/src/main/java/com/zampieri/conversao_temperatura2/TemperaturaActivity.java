package com.zampieri.conversao_temperatura2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TemperaturaActivity extends AppCompatActivity {
    TextView lblFarenheit;
    TextView lblKelvin;
    float valor = 0;
    float farenheit=0;
    float kelvin=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        lblFarenheit = (TextView) findViewById(R.id.lblRes1);
        lblKelvin = (TextView) findViewById(R.id.lblRes2);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            valor = Float.parseFloat(extras.getString("temperatura"));
            farenheit = (float) (valor * 1.8) + 32;
            kelvin = valor + (float) 273.15;

            lblFarenheit.setText(Float.toString(farenheit));
            lblKelvin.setText(Float.toString(kelvin));
        }
    }//fim do onCreate
}