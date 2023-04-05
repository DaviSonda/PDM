package com.zampieri.estadosbrasil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaEstados;
    ArrayList<Estados> estados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEstados = (ListView) findViewById(R.id.listaEstados);
        listaEstados.setAdapter(new EstadoAdapter(getApplicationContext(),lista_estados()));

        listaEstados.setTextFilterEnabled(true);
        listaEstados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String estado = estados.get(position).getEstado();
                view.setSelected(true);
                Toast.makeText(getApplicationContext(), estado, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Estados> lista_estados() {
        estados = new ArrayList<Estados>();

        estados.add(new Estados("Acre", "AC", "Rio Branco", (float) 152581.4, R.drawable.acre));
        estados.add(new Estados("Alagoas", "AL", "Maceió", (float) 27767.7, R.drawable.alagoas));
        estados.add(new Estados("Amapá", "AP", "Macapá", (float) 142814.6, R.drawable.amapa));
        estados.add(new Estados("Amazonas", "AM", "Manaus", (float) 1570745.7, R.drawable.amazonas));
        estados.add(new Estados("Bahia", "BA", "Salvador", (float) 564692.7, R.drawable.bahia));
        estados.add(new Estados("Ceará", "CE", "Fortaleza", (float) 148825.6, R.drawable.ceara));
        estados.add(new Estados("Distrito Federal", "DF", "Brasília", (float) 5822.1, R.drawable.distrito_federal));
        estados.add(new Estados("Espírito Santo", "ES", "Vitória", (float) 46077.5, R.drawable.espirito_santo));
        estados.add(new Estados("Goiás", "GO", "Goiânia", (float) 340086.7, R.drawable.goias));
        estados.add(new Estados("Maranhão", "MA", "São Luís", (float) 331983.3, R.drawable.maranhao));
        estados.add(new Estados("Mato Grosso", "MT", "Cuiabá", (float) 903357.9, R.drawable.mato_grosso));
        estados.add(new Estados("Mato Grosso do Sul", "MS", "Campo Grande", (float) 357125.0, R.drawable.mato_grosso_do_sul));
        estados.add(new Estados("Minas Gerais", "MG", "Belo Horizonte", (float) 586528.3, R.drawable.minas_gerais));
        estados.add(new Estados("Pará", "PA", "Belém", (float) 1247689.5, R.drawable.para));
        estados.add(new Estados("Paraíba", "PB", "João Pessoa", (float) 56439.8, R.drawable.paraiba));
        estados.add(new Estados("Paraná", "PR", "Curitiba", (float) 199314.9, R.drawable.parana));
        estados.add(new Estados("Pernambuco", "PE", "Recife", (float) 98311.6, R.drawable.pernambuco));
        estados.add(new Estados("Piauí", "PI", "Teresina", (float) 251529.2, R.drawable.piaui));
        estados.add(new Estados("Rio de Janeiro", "RJ", "Rio de Janeiro", (float) 43696.1, R.drawable.rio_de_janeiro));
        estados.add(new Estados("Rio Grande do Norte", "RN", "Natal", (float) 52796.8, R.drawable.rio_grande_do_norte));
        estados.add(new Estados("Rio Grande do Sul", "RS", "Porto Alegre", (float) 281748.5, R.drawable.rio_grande_do_sul));
        estados.add(new Estados("Rondônia", "RO", "Porto Velho", (float) 237576.2, R.drawable.rondonia));
        estados.add(new Estados("Roraima", "RR", "Boa Vista", (float) 224299.0, R.drawable.roraima));
        estados.add(new Estados("Santa Catarina", "SC", "Florianópolis", (float) 95346.2, R.drawable.santa_catarina));
        estados.add(new Estados("São Paulo", "SP", "São Paulo", (float) 248209.4, R.drawable.sao_paulo));
        estados.add(new Estados("Sergipe", "SE", "Aracaju", (float) 21910.3, R.drawable.sergipe));
        estados.add(new Estados("Tocantins", "TO", "Palmas", (float) 277620.9, R.drawable.tocantins));

        return estados;
    }
}