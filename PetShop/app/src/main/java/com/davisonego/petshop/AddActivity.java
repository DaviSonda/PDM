package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddActivity extends AppCompatActivity {

    String tipo = "1"; //1 = Perdido/Achado, 2 = Petshop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tipo = extras.getString("tipo").toString();
        }

        EditText txtTitulo = findViewById(R.id.txtTit);
        Spinner spnTitulo = findViewById(R.id.spnTit);
        EditText txtContato = findViewById(R.id.txtCont);
        EditText txtDesc = findViewById(R.id.txtDesc);

        if (tipo == "1") {
            txtTitulo.setVisibility(View.INVISIBLE);
            spnTitulo.setVisibility(View.VISIBLE);
        } else {
            spnTitulo.setVisibility(View.INVISIBLE);
            txtTitulo.setVisibility(View.VISIBLE);
        }

        Button button = findViewById(R.id.btnSbmt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit;
                if ( tipo == "1") {
                    tit = txtTitulo.getText().toString();
                } else {
                    tit = spnTitulo.getSelectedItem().toString();
                }
                String cont = txtContato.getText().toString();
                String desc = txtDesc.getText().toString();
                Pet pet = new Pet(tit, desc, "Nada", tipo, cont);

                // Perform operations with the input text

                // Example: Display a toast message with the input values
//                Toast.makeText(AddActivity.this, pet.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Titulo", pet.getTitulo());
                    jsonObject.put("Descricao", pet.getDescricao());
                    jsonObject.put("Tipo_pub", pet.getTipo());
                    jsonObject.put("Metodo_Contato", pet.getContato());
                    jsonObject.put("Img", pet.getImg());
                    String jsonString = jsonObject.toString();
                    new PostData().execute(jsonString);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class PostData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                // on below line creating a url to post the data.
                URL url = new URL(Globals.url + "/posts");

                // on below line opening the connection.
                HttpURLConnection client = (HttpURLConnection) url.openConnection();

                // on below line setting method as post.
                client.setRequestMethod("POST");

                // on below line setting content type and accept type.
                client.setRequestProperty("Content-Type", "application/json");
                client.setRequestProperty("Accept", "application/json");

                // on below line setting client.
                client.setDoOutput(true);

                // on below line we are creating an output stream and posting the data.
                try (OutputStream os = client.getOutputStream()) {
                    byte[] input = strings[0].getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // on below line creating and initializing buffer reader.
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(client.getInputStream(), "utf-8"))) {
                }

            } catch (Exception e) {

                // on below line handling the exception.
                e.printStackTrace();
                Toast.makeText(AddActivity.this, "Fail to post the data : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}