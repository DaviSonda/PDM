package com.davisonego.davisonegoa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Cidade> cidades;
    TextView txtMaiorAcidente;
    TextView txtMenorAcidente;
    TextView txtMediaVeiculos;
    TextView txtCidadePequena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtMaiorAcidente = findViewById(R.id.txtMaiorAcidente);
        txtMenorAcidente = findViewById(R.id.txtMenorAcidente);
        txtMediaVeiculos = findViewById(R.id.txtMediaVeiculos);
        txtCidadePequena = findViewById(R.id.txtCidadePequena);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Object, Object, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(MainActivity.this);

        @Override
        protected Cursor doInBackground(Object... params) {
            conexaoDB.open();
            return conexaoDB.getTodasCidades(); //retorna todos os livros
        }

        // usa o cursor retornado pelo doInBackgrounds
        @Override
        protected void onPostExecute(Cursor result) {
            if (result != null) {
                cidades = new ArrayList<>();
                result.moveToFirst();
                while (!result.isAfterLast()) {
                    Integer codigo = Integer.parseInt(result.getString(0));
                    String nome = result.getString(1);
                    Integer veiculos = Integer.parseInt(result.getString(2));
                    Integer acidentes = Integer.parseInt(result.getString(3));
                    cidades.add(new Cidade(codigo, nome, veiculos, acidentes));
                    result.moveToNext();
                }
                conexaoDB.close();
                preencheDados();
            }
        }
    }

    private void preencheDados() {
        Cidade ci = new Cidade();
        Cidade maiorAcidente = ci.getMaiorAcidente(cidades);
        Cidade menorAcidente = ci.getMenorAcidente(cidades);
        String txt1 = getString(R.string.maior_acidente, maiorAcidente.QuantidadeAcidentes.toString(), maiorAcidente.Nome);
        String txt2 = getString(R.string.menor_acidente, menorAcidente.QuantidadeAcidentes.toString(), menorAcidente.Nome);
        String txt3 = getString(R.string.media_veiculo, ci.getMediaVeiculos(cidades).toString());
        txtMaiorAcidente.setText(txt1);
        txtMenorAcidente.setText(txt2);
        txtMediaVeiculos.setText(txt3);
        txtCidadePequena.setText(ci.getMediaAcidentesCidadePequena(cidades));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addCity) {
            Intent intent = new Intent(getApplicationContext(), AddCidade.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}