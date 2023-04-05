package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BasicViews1 extends AppCompatActivity {
    Button btnBotao1;
    EditText txtUsuario;
    EditText txtSenha;
    RadioButton rdbOpcao1;
    RadioButton rdbOpcao2;
    CheckBox chkOpcao1;
    CheckBox chkEstrela;

    String usuario;
    String senha;
    String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views1);

        //TextViews - Caixas de Texto
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        //RadioButtons - Botões de Rádio
        rdbOpcao1 = (RadioButton) findViewById(R.id.rdbOpcao1);
        rdbOpcao2 = (RadioButton) findViewById(R.id.rdbOpcao2);

        //CheckBox
        chkOpcao1 = (CheckBox) findViewById(R.id.chkOpcao1);

        //Button - Botão
        btnBotao1 = (Button) findViewById(R.id.btnBotao1);
        btnBotao1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                usuario = txtUsuario.getText().toString();
                senha = txtSenha.getText().toString();
                mensagem = "Usuário: "+usuario +"\n" + "Senha: " + senha;
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

                mensagem = "Nenhum Botão de Rádio Selecionado!";
                if(rdbOpcao1.isChecked()){
                    mensagem = "Você selecionou o botão de rádio 1";
                }
                if(rdbOpcao2.isChecked()){
                    mensagem = "Você selecionou o botão de rádio 2";
                }
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
            }
        });

        //---CheckBox---
        chkOpcao1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()){
                    mensagem = "CheckBox marcado!";
                }
                else{
                    mensagem = "CheckBox desmarcado!";
                }
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
            }
        });

        chkEstrela = (CheckBox) findViewById(R.id.star);
        chkEstrela.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                    Toast.makeText(getApplicationContext(), "CheckBox Estrela está marcado!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "CheckBox Estrela está desmarcado!", Toast.LENGTH_LONG).show();
            }
        });

        //---RadioButton---
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButton = group.getCheckedRadioButtonId();
                switch (checkedRadioButton) {
                    case R.id.rdbOpcao1 : mensagem = "RadioButton 1";
                        break;
                    case R.id.rdbOpcao2 : mensagem = "RadioButton 2";
                        break;
                }
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }
}