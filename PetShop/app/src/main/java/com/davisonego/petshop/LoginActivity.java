package com.davisonego.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void setPermission(View view) {
        FileHelper file = new FileHelper();
        String fileName = "login.txt";
        if (view.getId() == R.id.btnUser) {
            file.WriteFile(this, fileName, "USER");
        } else if (view.getId() == R.id.btnAdmin) {
            file.WriteFile(this, fileName, "ADMIN");
        }
        Intent intent = new Intent(getApplicationContext(), PerdidoActivity.class);
        startActivity(intent);
    }
}