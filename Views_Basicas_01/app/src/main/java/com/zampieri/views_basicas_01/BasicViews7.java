package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BasicViews7 extends AppCompatActivity {
    String[] presidentes;

    Spinner s1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views7);

        s1 = (Spinner) findViewById(R.id.spinner1);

        presidentes = getResources().getStringArray(R.array.array_presidentes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, presidentes);

        //Para fazer com que a lista apresente botões de rádio
        //    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //             android.R.layout.simple_spinner_dropdown_item, presidentes);

        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int pos, long id)
            {
                int index = s1.getSelectedItemPosition();
                //Spinner sp = (Spinner) adapterView;

                Toast.makeText(getBaseContext(),
                        "Você selecionou o presidente : " + presidentes[index],
                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}