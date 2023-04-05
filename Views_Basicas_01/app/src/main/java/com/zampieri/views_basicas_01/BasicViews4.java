package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class BasicViews4 extends AppCompatActivity {
    String[] presidentes =
            {
                    "João Figueiredo",
                    "José Sarney",
                    "Fernando Collor de Mello",
                    "Itamar Franco",
                    "Fernando Henrique Cardoso",
                    "Luis Inácio da Silva",
                    "Dilma Roussef",
                    "Michel Temer",
                    "Jair Messias Bolsonaro"
            };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views4);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, presidentes);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txtCountries);

        //O método setThreshold determina a quantidade de caracteres que o usuário deve digitarpara o autocomplete funcionar
        textView.setThreshold(3);
        textView.setAdapter(adapter);
    }
}