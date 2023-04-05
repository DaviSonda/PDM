package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicViews6 extends AppCompatActivity {
    /** Called when the activity is first created. */
    ListView lista;

    String contatos[]={"Ana", "André","Henrique","Ricardo","Lisiane","LLavo"};
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views6);

        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, contatos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, contatos);
        lista = (ListView) findViewById(R.id.listView1);

        //lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //usuário pode selecionar vários itens da lista
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//usuário pode selecionar um item da lista
        lista.setTextFilterEnabled(true); //filtra a lista
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                //Toast.makeText(ExemploListViewActivity.this,"Seleciochavado " + contatos[pos],Toast.LENGTH_SHORT).show();
                String itemSelecionado =(String) (lista.getItemAtPosition(pos));

                if(itemSelecionado != null){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(BasicViews6.this);
                    dialogo.setTitle("Contato Selecichavado");
                    dialogo.setMessage(itemSelecionado);
                    dialogo.setNeutralButton("OK", null);
                    dialogo.show();
                }
            }
        });
    }
}