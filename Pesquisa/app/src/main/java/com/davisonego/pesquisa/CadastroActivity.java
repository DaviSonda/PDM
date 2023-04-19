package com.davisonego.pesquisa;

import android.os.Bundle;

import com.davisonego.pesquisa.Pessoa;
import com.davisonego.pesquisa.databinding.ActivityCadastroBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.davisonego.pesquisa.databinding.ActivityCadastroBinding;

public class CadastroActivity extends AppCompatActivity {

    EditText txtIdade;
    Spinner txtSexo;
    Spinner txtOlhos;
    Spinner txtCabelo;
    Button btnSalvar;
    Pessoa pessoa;

    private AppBarConfiguration appBarConfiguration;
    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        txtIdade = (EditText) findViewById(R.id.txtIdade);
        txtSexo = (Spinner) findViewById(R.id.txtSexo);
        txtOlhos = (Spinner) findViewById(R.id.txtOlhos);
        txtCabelo = (Spinner) findViewById(R.id.txtCabelo);
        btnSalvar= (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa = new Pessoa(Integer.parseInt(txtIdade.getText().toString()), txtSexo.getSelectedItem().toString(),txtOlhos.getSelectedItem().toString(),txtCabelo.getSelectedItem().toString());
                Cadastro.addPessoa(pessoa);
                finish();
            }
        });
    }

}