package com.zampieri.base_dados_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView livrosListView;
    public static final String LINHA_ID = "idLinha";
    private CursorAdapter livrosAdapter; // Adaptador para a ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        livrosListView = (ListView) findViewById(R.id.listView);
        livrosListView.setOnItemClickListener(viewLivrosListener);

        // mapeia cada coluna da tabela com um componente da tela
        String[] origem = new String[]{"titulo","editora","isbn"};
        int[] destino = new int[] { R.id.txtTitulo, R.id.txtEditora, R.id.txtISBN };
        int flags = 0;

        livrosAdapter = new SimpleCursorAdapter(MainActivity.this,R.layout.activity_view_livros,null,origem,destino,flags);
        livrosListView.setAdapter(livrosAdapter);
    }
    @Override
    protected void onResume(){
        //sempre que executar onResume, irá fazer uma busca no banco de dados
        //e vai atualizar a tela de exibição dos livros cadastrados
        super.onResume();
        new ObtemLivros().execute();
    }
    ////////////////////////////////////////////////////////////
    // Quando precisamos dos resultados de uma operação do BD na thread da
    // interface gráfica, vamos usar AsyncTask para efetuar a operação em
    // uma thread e receber os resultados na thread da interface gráfica
    private class ObtemLivros extends AsyncTask<Object, Object, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(MainActivity.this);
        @Override
        protected Cursor doInBackground(Object... params){
            conexaoDB.open(); //abre a base de dados
            return conexaoDB.getTodosTitulos(); //retorna todos os livros
        }
        // usa o cursor retornado pelo doInBackground
        @Override
        protected void onPostExecute(Cursor result){
            livrosAdapter.changeCursor(result); //altera o cursor para um novo cursor
            conexaoDB.close();
        }
    }
    ///////////////////////////////////////////////////////////
    //Quando o usuário clica em uma linha da consulta, uma nova intenção
    //é executada, para exibir os dados do livro selecionado
    AdapterView.OnItemClickListener viewLivrosListener = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id){
            Intent viewLivros = new Intent(getApplicationContext(), EditLivroActivity.class);
            viewLivros.putExtra(LINHA_ID, id);
            startActivity(viewLivros);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //Cria uma intenção para executar o cadastramento de um novo livro
        Intent addNovoLivro = new Intent(getApplicationContext(), AddNovoLivroActivity.class);
        startActivity(addNovoLivro);
        return super.onOptionsItemSelected(item);
    }
}