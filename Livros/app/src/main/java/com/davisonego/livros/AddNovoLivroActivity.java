package com.davisonego.livros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddNovoLivroActivity extends AppCompatActivity {
    private long idLinha;
    private EditText txtTitulo;
    private EditText txtEditora;
    private EditText txtISBN;
    private Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novo_livro);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtEditora = (EditText) findViewById(R.id.txtEditora);
        txtISBN = (EditText) findViewById(R.id.txtISBN);

        Bundle extras = getIntent().getExtras();

        // Se há extras, usa os valores para preencher a tela
        if (extras != null){
            idLinha = extras.getLong("idLinha");
            txtTitulo.setText(extras.getString("titulo"));
            txtEditora.setText(extras.getString("editora"));
            txtISBN.setText(extras.getString("isbn"));
        }

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(salvarLivroButtonClicked);
    }

    View.OnClickListener salvarLivroButtonClicked = new View.OnClickListener(){
        public void onClick(View v){
            if (txtTitulo.getText().length() != 0){
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //Background work here
                        salvaLivro(); // Salva o livro na base de dados

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //UI Thread work here
                                finish(); // Fecha a atividade
                            }
                        });
                    }
                });
            } // end if
            else {
                // Cria uma caixa de diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNovoLivroActivity.this);
                builder.setTitle(R.string.tituloErro);
                builder.setMessage(R.string.mensagemErro);
                builder.setPositiveButton(R.string.botaoErro, null);
                builder.show();
            }
        }
    };

    // Salva o livro na base de dados
    private void salvaLivro(){
        DBAdapter databaseConnector = new DBAdapter(this);
        try{
            databaseConnector.open();
            if (getIntent().getExtras() == null){
                databaseConnector.insereLivro(
                        txtTitulo.getText().toString(),
                        txtEditora.getText().toString(),
                        txtISBN.getText().toString());
            }
            else{
                databaseConnector.alteraTitulo(idLinha,
                        txtTitulo.getText().toString(),
                        txtEditora.getText().toString(),
                        txtISBN.getText().toString());
            }
            databaseConnector.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}