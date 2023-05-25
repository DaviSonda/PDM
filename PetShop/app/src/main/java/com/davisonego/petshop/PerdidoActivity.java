package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PerdidoActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdido);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.perdido);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);

        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetData().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perdido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itAdd) {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            Bundle extras = new Bundle();
            extras.putString("tipo", "1");//Tipo perdido
            intent.putExtras(extras);
            startActivity(intent);
            return true;
        } else if (id == R.id.itPetshop) {
            startActivity(new Intent(getApplicationContext(), PetshopActivity.class));
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

    class GetData extends AsyncTask<Void, Void, ArrayList<PetDisplay>> {
        @Override
        protected ArrayList<PetDisplay> doInBackground(Void... params) {
            try {
                // create URL object
//                URL url = new URL("https://reqres.in/api/users?page=2");
                URL url = new URL(Globals.url + "/posts/tipo/1");

                // create HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println(response.toString());
                String res = response.toString();

                JSONArray objArray = new JSONArray(res);
                ArrayList<PetDisplay> pets = new ArrayList<>();
                for (int i = 0; i < objArray.length(); i++) {
                    //Pega o objeto do array
                    JSONObject obj = objArray.getJSONObject(i);
                    String id = obj.getString("id");
                    String tit = obj.getString("Titulo");
                    String desc = obj.getString("Descricao");
                    String img = obj.getString("Img");
                    String cont = obj.getString("Metodo_Contato");
                    //Adiciona os pets a lista
                    pets.add(new PetDisplay(img, tit, desc, cont, id));
                }
                return pets;
            } catch (Exception e) {
                System.out.println("ERRO?");
                System.out.println(e);
                // handle exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<PetDisplay> result) {
            // handle result
            if (result != null) {
                System.out.println("CHEGUEI AMIGO");
                System.out.println(result.size());
                PetAdapter petAdapter = new PetAdapter(PerdidoActivity.this, R.layout.list_row, result);
                listView.setAdapter(petAdapter);
            }
        }
    }
}