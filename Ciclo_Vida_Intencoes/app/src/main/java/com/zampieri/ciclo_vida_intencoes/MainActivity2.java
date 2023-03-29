package com.zampieri.ciclo_vida_intencoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    String tag= "Eventos2";
    Button btnAnterior;
    Button btnBrowser;
    Button btnFone;
    Button btnContatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(tag,"Estou no evento onCreate() da Activity2");
        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(); // Fecha esta Activity
            }
        });

        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(i);
            }
        });

        btnFone = (Button) findViewById(R.id.btnFone);
        btnFone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse("tel:+651234567"));
                startActivity(i);
            }
        });

        btnContatos = (Button) findViewById(R.id.btnContatos);
        btnContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("content://contacts/people/1"));
                startActivity(i);
            }
        });
    }
    public void onStart(){
        super.onStart();
        Log.d(tag, "Estou no evento onStart() da Activity2");
    }
    public void onRestart(){
        super.onRestart();
        Log.d(tag, "Estou no evento onRestart() da Activity2");
    }
    public void onResume(){
        super.onResume();
        Log.d(tag, "Estou no evento onResume() da Activity2");
    }
    public void onPause(){
        super.onPause();
        Log.d(tag, "Estou no evento onPause() da Activity2");
    }
    public void onStop(){
        super.onStop();
        Log.d(tag, "Estou no evento onStop() da Activity2");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(tag, "Estou no evento onDestroy() da Activity2");
    }
}