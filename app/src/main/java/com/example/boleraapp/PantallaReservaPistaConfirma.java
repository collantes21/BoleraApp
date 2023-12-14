package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class PantallaReservaPistaConfirma extends AppCompatActivity {

    private TextView textViewDate, textViewHora, textViewPersonas;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reserva_pista_confirma);
        // Actualizar TextView con los datos recogidos
        textViewHora = findViewById(R.id.textViewHora);
        textViewPersonas = findViewById(R.id.textViewPersonas);
        textViewDate = findViewById(R.id.textViewDate);

        Intent intent = getIntent();
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");
        int numPersonas = intent.getIntExtra("numPersonas", 0); // Elimina el valor por defecto



        textViewDate.setText("Fecha: " + fecha);
        textViewHora.setText("Hora: " + hora);
        textViewPersonas.setText("Num. Personas: " + numPersonas);


    }
}