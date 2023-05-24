package com.zampieri.consulta_cep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnConsultar;
    EditText txtCep;
    TextView lblDados;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        txtCep = (EditText) findViewById(R.id.txtCep);
        lblDados = (TextView) findViewById(R.id.lblDados);

        btnConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String cep = txtCep.getText().toString();
                if( !cep.equals("") ){
                    new AtualizaDadosTask().execute(cep);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Informe um CEP!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class AtualizaDadosTask extends AsyncTask<String, Void, JsonReader> {
        @Override
        protected JsonReader doInBackground(String... params) {
            URL viaCepEndpoint = null;
            JsonReader jsonReader = null;
            try {

                viaCepEndpoint = new URL("https://viacep.com.br/ws/" + params[0] + "/json");

                HttpsURLConnection conexao = (HttpsURLConnection) viaCepEndpoint.openConnection();
                if (conexao.getResponseCode() == 200) {
                    InputStream responseBody = conexao.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");

                    jsonReader = new JsonReader(responseBodyReader);
                    conexao.disconnect();
                } else {
                    conexao.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonReader;
        }

        protected void onPostExecute(JsonReader resultados) {
            endereco = new Endereco();
            String chave;
            String valor;
            try {
                resultados.beginObject();
                while (resultados.hasNext()) {
                    chave = resultados.nextName();
                    if (chave.equals("logradouro")) {
                        endereco.setLogradouro(resultados.nextString());
                    }
                    else{
                        if (chave.equals("complemento")) {
                            endereco.setComplemento(resultados.nextString());
                        }
                        else{
                            if (chave.equals("bairro")) {
                                endereco.setBairro(resultados.nextString());
                            }
                            else{
                                if (chave.equals("localidade")) {
                                    endereco.setLocalidade(resultados.nextString());
                                }
                                else{
                                    if (chave.equals("uf")) {
                                        endereco.setUf(resultados.nextString());
                                    }
                                    else{
                                        if (chave.equals("cep")) {
                                            endereco.setCep(resultados.nextString());
                                        }
                                        else{
                                            resultados.skipValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                resultados.endObject();

            } catch (IOException e) {
                e.printStackTrace();
            }
            lblDados.setText(endereco.toString());
        }
    }
}