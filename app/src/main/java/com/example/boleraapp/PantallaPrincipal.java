package com.example.boleraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PantallaPrincipal extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reserva) {

            Intent intent = new Intent(this, PantallaReservaPista.class);

            startActivity(intent);

        } else if (id == R.id.restaurante) {

            Intent intent = new Intent(this, PantallaRegistroLogin.class);

            startActivity(intent);

        } else if (id == R.id.localizacion) {

            Intent intent = new Intent(this, PantallaUbicacion.class);

            startActivity(intent);

        } else {

            return super.onOptionsItemSelected(item);
        }
        return false;
    }

}







