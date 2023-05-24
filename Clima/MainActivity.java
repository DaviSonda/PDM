package com.zampieri.consulta_clima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnObtemDados;
    TextView lblDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblDados = (TextView) findViewById(R.id.lblDados);
        btnObtemDados = (Button) findViewById(R.id.btnObtemDados);

        btnObtemDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v.setClickable(false);
                new ObtemDadosTask().execute();
            }
        });
    }

    // Classe Privada que Busca os Dados no WebService
    private class ObtemDadosTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            URL inpeEndpoint = null;
            JsonReader jsonReader = null;
            String dados = "";
            try {

                inpeEndpoint = new URL("http://servicos.cptec.inpe.br/XML/cidade/1431/previsao.xml");

                HttpURLConnection conexao = (HttpURLConnection) inpeEndpoint.openConnection();
                if (conexao.getResponseCode() == 200) {
                    InputStream responseBody = conexao.getInputStream();

                    dados = getDados(responseBody);

                } else {
                    conexao.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return dados;
        }

        protected void onPostExecute(String resultados) {
            if (resultados!=null) {
                lblDados.setText(resultados);
            }
        }

        protected String getDados(InputStream entity) throws IllegalStateException, IOException {
            // Extrai os dados da página (XML)
            String dadosResultantes="";

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
    }
///////////////////////////////////////////////////////////////////////////////////////////////

    private String parseXML(XmlPullParser parser) throws XmlPullParserException, IOException{
        int eventType = parser.getEventType();
        CidadeTempo cidadeTempo = null;
        Previsao previsao = null;

        while (eventType != XmlPullParser.END_DOCUMENT){ //Executa enquanto não encontra o Fim do Documento

            String name = null;
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    cidadeTempo = new CidadeTempo(); //Início do XML, declara o objeto que recebe os dados
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();

                    if(name.equals("nome")){
                        cidadeTempo.setNome(parser.nextText()); //Nome da Cidade
                    }
                    if(name.equals("uf")){
                        cidadeTempo.setUf(parser.nextText()); //UF da Cidade
                    }
                    if(name.equals("atualizacao")){
                        cidadeTempo.setAtualizacao(parser.nextText()); //Data da Atualização
                    }
                    if(name.equals("previsao")){ //Previsão
                        previsao = new Previsao(); //Cria um objeto para receber os dados da previsão
                    }
                    if(name.equals("dia") && previsao!=null){
                        previsao.setData(parser.nextText());  //Data da Previsão
                    }
                    if(name.equals("tempo") && previsao!=null){
                        previsao.setTempo(parser.nextText()); //Tempo
                    }
                    if(name.equals("maxima") && previsao!=null){
                        previsao.setMaxima(parser.nextText()); //Temperatura Máxima
                    }
                    if(name.equals("minima") && previsao!=null){
                        previsao.setMinima(parser.nextText()); //Temperatura Mínima
                    }
                    if(name.equals("iuv") && previsao!=null){
                        previsao.setIuv(parser.nextText()); //Índice Ultra Violeta
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equals("previsao")){ //Insere o objeto Previsão no objeto cidadeTempo
                        cidadeTempo.inserePrevisao(previsao);
                    }

                    break;
            }
            eventType = parser.next();
        }
        return  cidadeTempo.toString();
    }
}