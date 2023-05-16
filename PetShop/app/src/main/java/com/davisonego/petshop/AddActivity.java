package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    String tipo; //1 = Perdido, 2 = Achado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            tipo = extras.getString("tipo").toString();
        }

        EditText txtTitulo = findViewById(R.id.txtTit);
        EditText txtContato = findViewById(R.id.txtCont);
        EditText txtDesc = findViewById(R.id.txtDesc);

        Button button = findViewById(R.id.btnSbmt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit = txtTitulo.getText().toString();
                String cont = txtContato.getText().toString();
                String desc = txtDesc.getText().toString();

                // Perform operations with the input text

                // Example: Display a toast message with the input values
                Toast.makeText(AddActivity.this, "Tit : " + tit + "\nCont : " + cont + "\nDesc : " + desc, Toast.LENGTH_SHORT).show();
            }
        });


    }
}