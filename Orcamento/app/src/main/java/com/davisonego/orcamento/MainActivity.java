package com.davisonego.orcamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView catListView;
    private CursorAdapter catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        catListView = (ListView) findViewById(R.id.displayCat);
//        catListView.setOnClickListener(addProdListener);

        String[] origem = new String[]{"descricao"};
        int[] destino = new int[]{R.id.txtDesc};

        catAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.activity_view_categoria, null, origem, destino, 0);
        catListView.setAdapter(catAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ObtemCat().execute();
    }

    private class ObtemCat extends AsyncTask<Object, Object, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(MainActivity.this);

        @Override
        protected Cursor doInBackground(Object... params) {
            conexaoDB.open(); //abre a base de dados
            return conexaoDB.getTodasCategorias();
        }

        // usa o cursor retornado pelo doInBackground
        @Override
        protected void onPostExecute(Cursor result) {
            if (result != null && result.getCount() > 0) {
                try {
                    catAdapter.changeCursor(result);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            conexaoDB.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addCat) {
            startActivity(new Intent(getApplicationContext(), CategoriaActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}