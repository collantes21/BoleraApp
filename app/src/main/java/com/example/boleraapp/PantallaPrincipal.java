package com.example.boleraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.media.MediaPlayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPrincipal extends AppCompatActivity {

    Button btreservar;
    MediaPlayer mediaPlayer;
    private TextView usuario;

    private String fecha, hora;
    private int numPersonas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usuario=findViewById(R.id.usuario);
        btreservar=findViewById(R.id.btreservar);
        iniciarReproduccion();

        String usuarioActual = SessionManager.getInstance().getCurrentUsuario();

        //Mostrar nombre de usuario en el textView
        usuario.setText("Hola " + usuarioActual + " ya puedes realizar tu reserva.");
    }


    @Override
    protected void onDestroy() {
        // Libera los recursos del MediaPlayer al destruir la actividad
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void iniciarReproduccion() {
        // Inicia la reproducción del archivo de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.nomada);
        mediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    public void reservar(View view){

        Intent intent = new Intent(this, PantallaReservaPista.class);


        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.historial) {

            Intent intent = new Intent(this, PantallaHistorial.class);


            startActivity(intent);

        } else if (id == R.id.localizacion) {

            Intent intent = new Intent(this, MapaGoogle.class);

            startActivity(intent);

        } else {

            return super.onOptionsItemSelected(item);
        }
        return false;
    }

}







