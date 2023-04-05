package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class BasicViews3 extends AppCompatActivity {
    private static int progresso = 0;
    private ProgressBar barraProgresso;
    private int statusProgresso = 0;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views3);

        barraProgresso = (ProgressBar) findViewById(R.id.progressbar);
        barraProgresso.setMax(200);//barra vai até 200 (valor máximo)

        new Thread(new Runnable(){
            public void run(){
                while (statusProgresso < 200){ //100 � a metade da barra
                    statusProgresso = fazAlgumaCoisa();

                    //--- Atualiza a barra de progresso ---
                    handler.post(new Runnable(){
                        public void run() {
                            barraProgresso.setProgress(statusProgresso);
                        }
                    });
                }
                //--- esconde a barra de progresso---
                handler.post(new Runnable(){
                    public void run(){
                        //---View.VISIBLE ; 4 - Invis�vel ; 8 - Finaliza e desaparece ---
                        barraProgresso.setVisibility(View.GONE);
                    }
                });
            }

            private int fazAlgumaCoisa(){
                try{
                    //--- simula a realização de alguma coisa ---
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return ++progresso;
            }
        }).start();

    }
}