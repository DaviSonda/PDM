package com.davisonego.contatos;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.davisonego.contatos.databinding.ActivityCadastroBinding;

public class CadastroActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtFone;
    Button btnSalvar;
    Contato contato;

    private AppBarConfiguration appBarConfiguration;
    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtFone = (EditText) findViewById(R.id.txtFone);
        btnSalvar= (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contato = new Contato(txtNome.getText().toString(), txtFone.getText().toString());
                Cadastro.addContato(contato);
                finish();
            }
        });
    }

}