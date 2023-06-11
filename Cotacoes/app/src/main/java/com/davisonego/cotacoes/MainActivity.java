package com.davisonego.cotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(paisListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetData().execute();
    }

    AdapterView.OnItemClickListener paisListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
            Intent intent = new Intent(getApplicationContext(), PaisActivity.class);
            if (id == 0) {
                intent.putExtra("nome", "Estados Unidos");
            } else if (id == 1) {
                intent.putExtra("nome", "Servia");
            } else if (id == 2) {
                intent.putExtra("nome", "Alemanha");
            }
            startActivity(intent);
        }
    };

    private class GetData extends AsyncTask<Object, Object, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(MainActivity.this);

        @Override
        protected Cursor doInBackground(Object... params) {
            conexaoDB.open(); //abre a base de dados
            conexaoDB.mock();
            return conexaoDB.getTodosPaises(); //retorna todos os livros
        }

        // usa o cursor retornado pelo doInBackgrounds
        @Override
        protected void onPostExecute(Cursor result) {
            if (result != null) {
                ArrayList<Pais> paises = new ArrayList<>();
                result.moveToFirst();
                while (!result.isAfterLast()) {
                    String bandeira = result.getString(1);
                    String nome = result.getString(2);
                    String cotacao = result.getString(3);
                    paises.add(new Pais(bandeira, nome, cotacao));
                    result.moveToNext();
                }
                PaisAdapter pAdapter = new PaisAdapter(MainActivity.this, R.layout.pais_row, paises);
                listView.setAdapter(pAdapter);
                conexaoDB.close();
            }
        }
    }
}