package com.zampieri.conversao_temperatura2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtTemperatura;
    Button btnCalculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTemperatura = (EditText) findViewById(R.id.txtTemperatura);
        btnCalculo = (Button) findViewById(R.id.btnConverter);
        btnCalculo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String temperatura = txtTemperatura.getText().toString();
                if (txtTemperatura.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Entre um número valido",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Intent intencao = new Intent(getApplicationContext(), TemperaturaActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("temperatura",temperatura);
                    intencao.putExtras(extras);
                    startActivity(intencao);
                }
            }
        });
    }
}