package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class BasicViews2 extends AppCompatActivity {
    private static int progress = 0;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views2);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        //--- faz alguma coisa em uma thread paralela---
        new Thread(new Runnable(){
            public void run(){
                //---faz alguma coisa aqui---
                while (progressStatus < 10){
                    progressStatus = algumaCoisa();
                }

                //---hides the progress bar---
                handler.post(new Runnable(){
                    public void run(){
                        //---0 - Visível; 4 - Invisível; 8 - Finaliza e desaparece---
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

            //---executa uma tarefa longa aqui---
            private int algumaCoisa(){
                try {
                    //---simula a realização de alguma coisa---
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                return ++progress;
            }

        }).start();
    }
}