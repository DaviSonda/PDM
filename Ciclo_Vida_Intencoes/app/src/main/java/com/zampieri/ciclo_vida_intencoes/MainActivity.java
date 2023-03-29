package com.zampieri.ciclo_vida_intencoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String tag= "Eventos1";
    Button btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag,"Estou no evento onCreate()");

        btnProximo = (Button) findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
            }
        });
    }

    public void onStart(){
        super.onStart();
        Log.d(tag, "Estou no evento onStart()");
    }
    public void onRestart(){
        super.onRestart();
        Log.d(tag, "Estou no evento onRestart()");
    }
    public void onResume(){
        super.onResume();
        Log.d(tag, "Estou no evento onResume()");
    }
    public void onPause(){
        super.onPause();
        Log.d(tag, "Estou no evento onPause()");
    }
    public void onStop(){
        super.onStop();
        Log.d(tag, "Estou no evento onStop()");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(tag, "Estou no evento onDestroy()");
    }

}