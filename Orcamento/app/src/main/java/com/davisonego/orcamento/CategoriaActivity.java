package com.davisonego.orcamento;

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

public class CategoriaActivity extends AppCompatActivity {

    private EditText txtDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        txtDesc = (EditText) findViewById(R.id.desc);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(salvarCategoriaButtonClicked);
    }

    View.OnClickListener salvarCategoriaButtonClicked = new View.OnClickListener(){
        public void onClick(View v){
            if (txtDesc.getText().length() != 0) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        salvaLivro();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    }
                });
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(CategoriaActivity.this);
                builder.setTitle("Necessario informar a descricao da categoria!");
                builder.setMessage("Voce deve digitar a descricao da categoria");
                builder.setPositiveButton("ok", null);
                builder.show();
            }
        }
    };

    private void salvaLivro(){
        DBAdapter dbConnector = new DBAdapter(this);
        try {
            dbConnector.open();
            dbConnector.insereCategoria(txtDesc.getText().toString());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}