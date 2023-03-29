package com.davisonego.converte_temperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtCelsius;
    EditText txtFahrenheit;
    Button btnConverter;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCelsius = (EditText) findViewById(R.id.txtCelsius);
        txtFahrenheit = (EditText) findViewById(R.id.txtFahrenheit);
        btnConverter = (Button) findViewById(R.id.btnConverter);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float celsius = 0;
                float far = 0;
                if(txtCelsius.getText().toString().isEmpty() ==true) {
                    if(txtFahrenheit.getText().toString().isEmpty() == false) {
                        far = Float.parseFloat(txtFahrenheit.getText().toString());
                        celsius = (far-32)/(float)1.8;
                    }
                }

                if(txtFahrenheit.getText().toString().isEmpty() ==true) {
                    if(txtCelsius.getText().toString().isEmpty() == false) {
                        celsius = Float.parseFloat(txtCelsius.getText().toString());
                        far = (celsius * (float) 1.8) + 32;
                    }
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCelsius.getText().clear();
                txtFahrenheit.getText().clear();
            }
        });
    }
}