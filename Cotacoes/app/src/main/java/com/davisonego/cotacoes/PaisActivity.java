package com.davisonego.cotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PaisActivity extends AppCompatActivity {

    Pais pais;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);
        TextView txt = findViewById(R.id.txtNom);
        Bundle extras = getIntent().getExtras();
        nome = "Ueba";
        if (extras != null) {
            nome = extras.getString("nome").toString();
        }
        //Aqui ta comentado pq o get pelo nome n ta funcionando
//        new GetData().execute(nome);
        txt.setText(nome);

        Button btnMain = findViewById(R.id.btnSbmtMain);
        Button btnForeign = findViewById(R.id.btnSbmtForeign);
        EditText inptMain = findViewById(R.id.txtMain);
        EditText inptForeign = findViewById(R.id.txtForeign);
        TextView rsltMain = findViewById(R.id.txtMainConvertResult);
        TextView rsltForeign = findViewById(R.id.txtForeignConvertResult);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inptMain.getText() != null) {
                    Double val = Double.parseDouble(inptMain.getText().toString());
                    //Precisaria pegar o pais pra funcionar
//                    Double coe = Double.parseDouble(pais.getCotacao());
//                    Double rslt = val * coe;
//                    rsltMain.setText(val + " em moeda estrangeira");
                    rsltMain.setText(val + " em moeda estrangeira");
                    rsltMain.setVisibility(View.VISIBLE);
                }
            }
        });

        btnForeign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inptForeign.getText() != null) {
                    Double val = Double.parseDouble(inptForeign.getText().toString());
                    //Precisaria pegar o pais pra funcionar
//                    Double coe = Double.parseDouble(pais.getCotacao());
//                    Double rslt = val / coe;
//                    rsltForeign.setText(rslt + " em real");
                    rsltForeign.setText(val + " em real");
                    rsltForeign.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(PaisActivity.this);

        @Override
        protected Cursor doInBackground(String... strings) {
            conexaoDB.open(); //abre a base de dados
            return conexaoDB.getByName(strings[0]); //retorna todos os livros
        }

        // usa o cursor retornado pelo doInBackgrounds
        @Override
        protected void onPostExecute(Cursor result) {
            if (result != null) {
                result.moveToFirst();
                String bandeira = result.getString(1);
                String nome = result.getString(2);
                String cotacao = result.getString(3);
                pais = new Pais(bandeira, nome, cotacao);
                conexaoDB.close();
            }
        }
    }
}