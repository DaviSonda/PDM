package com.davisonego.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtPeso;
    EditText txtAltura;

    Button btnConverter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtAltura = (EditText) findViewById(R.id.txtAltura);
        btnConverter = (Button) findViewById(R.id.btnConverter);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String peso = txtPeso.getText().toString();
                String altura = txtAltura.getText().toString();

                if (txtAltura.getText().length() == 0 && txtPeso.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Voce deve informar um peso e uma altura validos", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    Intent intencao = new Intent(getApplicationContext(), ImcActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("peso", peso);
                    extras.putString("altura", altura);
                    intencao.putExtras(extras);
                    startActivity(intencao);
                }
            }
        });
    }
}