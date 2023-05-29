package com.davisonego.daviclima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    String cidade;
    ListView list;
    CidadeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText txtCidade = findViewById(R.id.txtCidade);

        Button button = findViewById(R.id.btnSrch);
        list = findViewById(R.id.listView);
        ArrayList<Cidade> c = new ArrayList<>();
        adapter = new CidadeAdapter(AddActivity.this, R.layout.city_row, c);
        list.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cidade = txtCidade.getText().toString();
                    new SearchCity().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class SearchCity extends AsyncTask<String, Void, ArrayList<Cidade>> {
        @Override
        protected ArrayList<Cidade> doInBackground(String... params) {
            try {
                URL url = new URL("http://servicos.cptec.inpe.br/XML/listaCidades?city=" + cidade.toLowerCase());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                ArrayList<Cidade> cidades = new ArrayList<>();
                if (conn.getResponseCode() == 200) {
                    InputStream responseBody = conn.getInputStream();
                    cidades = getDados(responseBody);
                    System.out.println("Response");
                    System.out.println(cidades.size());
                    for (int i = 0; i < cidades.size(); i++) {
                        System.out.println(cidades.get(i).toString());
                    }
                }else {
                    conn.disconnect();
                }
                return cidades;
            } catch (Exception e) {
                System.out.println("ERRO");
                System.out.println(e);
                e.printStackTrace();
                return null;
            }
        }

        protected ArrayList<Cidade> getDados(InputStream entity) throws IllegalStateException, IOException {
            // Extrai os dados da página (XML)
            ArrayList<Cidade> dadosResultantes = new ArrayList<>();

            XmlPullParserFactory pullParserFactory;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(entity,
                        null);

                dadosResultantes = parseXML(parser); //chama o método que retorna o parse (string) do XML

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return dadosResultantes;

        }

        private ArrayList<Cidade> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException{
            int eventType = parser.getEventType();
            Cidade cidade = null;
            ArrayList<Cidade> cidades = new ArrayList<>();

            while (eventType != XmlPullParser.END_DOCUMENT){ //Executa enquanto não encontra o Fim do Documento

                String name = null;
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("AQANTES?");
                        cidade = new Cidade(); //Início do XML, declara o objeto que recebe os dados
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();

                        if(name.equals("nome")){
                            cidade.setNome(parser.nextText()); //Nome da Cidade
                        }
                        if(name.equals("uf")){
                            cidade.setUF(parser.nextText()); //UF da Cidade
                        }
                        if(name.equals("id")){
                            cidade.setCodigo(parser.nextText()); //Codigo da cidade
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        System.out.println("TOAQ?");
                        cidades.add(cidade);
                        break;
                }
                eventType = parser.next();
            }
            return  cidades;
        }

        @Override
        protected void onPostExecute(ArrayList<Cidade> result) {
            if (result != null) {
                adapter.addAll(result);
                adapter.notifyDataSetChanged();
            }
        }
    }
}