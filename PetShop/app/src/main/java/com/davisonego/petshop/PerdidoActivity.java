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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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

        ArrayList<PetDisplay> arrayList = new ArrayList<>();

        String b64Img = "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABmJLR0QA/wD/AP+gvaeTAAADrklEQVRoge2YzW8bRRiHnxmvnY1j3BQ5TVCK5EuR2txyIS1IcSsucAekFpC4RL0i+YbgwLU5ceupVaNSkT+AGxCJggyiByRIJS4cGpRSR0pjnNjxfrwcYje7G++ujb8K8nPyzLye9/cbvbMzuzBmzJgxY3pAdRJUuFWd0wn3M5T7FjDfPkpCMoT0h8Ujf4L6yrWcTzc+PPM4TlusgcIXlZx25AfgXEzikAxdG2gp+91Vqdc2rmZ3ovQZUYMA2qEInLNrDapbuziW9cx2UIK06ZUTSyQh/wMUJJKa6fnTJM3UK4rGR8DHkfriDKDkXYD9x3s4lh0b3iuO5VD5q9JMzdW4+EgDhVu7eYS867g4tUafJMZj1xq4jguQL9zbzUfFRhrQiUQBwD44DK3YQSBAo7lg2nUKUbHRJaRlGcA+GN7qt2i0cgrLUXFxe6AAYO0f9kVUNxwe1H0awgg14K1/t271U1tHOHW7o31gXFnb+x7kEkjzmS1boFZwE7MgQ6//Fq19YGZMtOsULq/tlRVyE2S+qRMR974BXAr896zg3lRavkZGU/8tGgdHBhCWFeoNEN8tQCn1uga1FfyjgpcZYf238O8DORscF3ikUbICnDCBkJcR1X8L7z4Ijgk80qJWThz0l2//vaAS/ARuuv20Q7oLneyvi7hL374/+4u31/cUevNzmVCGfAkSIn6kmErpu2+vS8rb6TPQOF25jrAwXF1dsbBjla97O3wGBHVtuHq6R4lfY+A6rRa8NTg7pSleNLmQS7BZdrhRqvNk3xmowNkpTXEpw4WcweaOzY1SlSf7rjfEVyGBk9hf+8WLJotzBqahWHzJoLhkDkr3cc6lDItzyaOcc0mKr2aCIVPeRuRd6HwuEdkeBOdzRmQ7SKSBh2V/uTzcGWz5HOWwI9tBIg2sluo82Lap2cLP2zarpXpUeF9YLVV5sG01c1qs/liNjPcdZFfWKp5TJP6g2fujjF2zjvrU8Yj33fj4nVj84+rol2EmyeXPtJ0/LO837808mzX+nTiCienez7v09FR8UAS9GTiVRumOPi21RWmFme1tEXoyoLRi4tS/FzCZTaN7WADo0QDA5EyGZDrV2Se+JgpITU6Qmcn2mj7+w1Yc2kiQzc/Qv9tol/n7MssI8RnQ9d1R6egYXXvqb3sbmd/uPdcmdO0pL/y67uvz7YFUeZMXNz4JnWD97p3eHhkd8s61DzreIP+vPfBfxG9AcT8sUFDfDVzN86ZjzJgxY2L5B+IFV/aFwPFpAAAAAElFTkSuQmCC";
        arrayList.add(new PetDisplay(b64Img, "Son Gohan1", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan2", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan3", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan4", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan5", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan6", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan7", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan8", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan9", "Mora em DBZ", "Me chama no zap"));
        arrayList.add(new PetDisplay(b64Img, "Son Gohan10", "Mora em DBZ", "Me chama no zap"));

        PetAdapter petAdapter = new PetAdapter(this, R.layout.list_row, arrayList);

        listView.setAdapter(petAdapter);

//        new GetData().execute();
        //Chamada no onResume
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

    class GetData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                // create URL object
                URL url = new URL("https://reqres.in/api/users?page=2");

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

                JSONObject obj = new JSONObject(response.toString());
                //FOR PARA PERCORRER ARRAY DE RESPONSE
                //A CADA LOOP CRIAR NOVO PET E ADICIONAR A LIST DE PETS
                //RETORNAR LISTA DE PETS PARA POSTEXECUTE
                System.out.println("AIAIAI: " + obj.getString("page"));
                // return response
                return response.toString();
            } catch (Exception e) {
                // handle exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //ATRIBUIR LISTA DE PET AO ADAPTER
            //Criar list view e setar o adapter
            // handle result
            if (result != null) {
                System.out.println(result);
            }
        }
    }
}