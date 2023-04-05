package com.zampieri.views_basicas_01;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.zampieri.views_basicas_01.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu01:
                startActivity(new Intent(getApplicationContext(),BasicViews1.class));
                return true;
            case R.id.menu02:
                startActivity(new Intent(this,BasicViews2.class));
                return true;
            case R.id.menu03:
                startActivity(new Intent(MainActivity.this,BasicViews3.class));
                return true;
            case R.id.menu04:
                startActivity(new Intent(getApplicationContext(),BasicViews4.class));
                return true;
            case R.id.menu05:
                startActivity(new Intent(getApplicationContext(),BasicViews5.class));
                return true;
            case R.id.menu06:
                startActivity(new Intent(getApplicationContext(),BasicViews6.class));
                return true;
            case R.id.menu07:
                startActivity(new Intent(getApplicationContext(),BasicViews7.class));
                return true;
            case R.id.menu08:
                startActivity(new Intent(getApplicationContext(),basicView8.class));
                return true;

        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}