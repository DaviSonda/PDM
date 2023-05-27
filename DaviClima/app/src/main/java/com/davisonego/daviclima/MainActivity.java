package com.davisonego.daviclima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        String[] origem = new String[]{"nome","uf","codigo"};
        int[] destino = new int[] { R.id.txtNome, R.id.txtUF, R.id.txtCodigo };
        adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.view_cidades, null, origem, destino, 0);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetData().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itAdd) {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    class GetData extends AsyncTask<Void, Void, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(MainActivity.this);
        @Override
        protected Cursor doInBackground(Void... params) {
            try {
                conexaoDB.open();
                return conexaoDB.getTodasCidades();
            } catch (Exception e) {
                System.out.println("ERRO?");
                System.out.println(e);
                // handle exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Cursor result) {
            // handle result
            if (result != null) {
                adapter.changeCursor(result);
                conexaoDB.close();
            }
        }
    }
}