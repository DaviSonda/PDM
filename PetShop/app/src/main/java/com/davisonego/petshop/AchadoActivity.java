package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AchadoActivity extends AppCompatActivity {

    //Permissao
    String perm = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achado);
        //Busca permissao do usuario
        getPermission();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.achado);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
    }

    public void getPermission() {
        perm = new FileHelper().ReadFile(this, "login.txt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_achado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itAdd) {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            Bundle extras = new Bundle();
            extras.putString("tipo", "2");//Tipo achado
            intent.putExtras(extras);
            startActivity(intent);
            return true;
        } else if (id == R.id.itPerdido) {
            startActivity(new Intent(getApplicationContext(), PerdidoActivity.class));
            finish();
            return true;
        } else if (id == R.id.itSair) {
            new FileHelper().DeleteFile(this, "login.txt");
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    class GetData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                // create URL object
                URL url = new URL("https://reqres.in/api/users?page=2");

                // create HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                System.out.println("1");

                // read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // return response
                return response.toString();
            } catch (Exception e) {
                // handle exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // handle result
            if (result != null) {
                System.out.println(result);
            }
        }
    }
}