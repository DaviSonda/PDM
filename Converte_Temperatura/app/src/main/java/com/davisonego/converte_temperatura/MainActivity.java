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
                try {
                    celsius = Float.parseFloat(txtCelsius.getText().toString());
                } catch (Exception e) {}
                try {
                    far = Float.parseFloat(txtFahrenheit.getText().toString());
                } catch (Exception e) { }
                if(celsius > 0 && far > 0) {}
                else if(celsius > 0) {
                    far = (float) ((celsius * 1.8) + 32);
                    txtFahrenheit.setText(Float.toString(far));
                } else if (far > 0) {
                    celsius = (float) ((far - 32) / 1.8);
                    txtCelsius.setText(Float.toString(celsius));
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