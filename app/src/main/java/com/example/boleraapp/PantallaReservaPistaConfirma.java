package com.example.boleraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class PantallaReservaPistaConfirma extends AppCompatActivity {

    private TextView textViewDate, textViewHora, textViewPersonas;
    private String identificadorReserva;
    private DbHelper dbHelper;

    private String fecha, hora;
    private int numPersonas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reserva_pista_confirma);
        // Actualizar TextView con los datos recogidos
        textViewHora = findViewById(R.id.textViewHora);
        textViewPersonas = findViewById(R.id.textViewPersonas);
        textViewDate = findViewById(R.id.textViewDate);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);


        String usuarioActual = SessionManager.getInstance().getCurrentUsuario();

        Intent intent = getIntent();
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");
        int numPersonas = intent.getIntExtra("numPersonas", 0);

        textViewDate.setText("Fecha:    " + fecha);
        textViewHora.setText("Hora:   " + hora);
        textViewPersonas.setText("Num. Personas:        " + numPersonas + " " + usuarioActual);

    }

    public void aceptarSuspension() {
        Toast t=Toast.makeText(this,"La reserva ha sido cancelada", Toast.LENGTH_SHORT);
        t.show();
        finish();
    }

    public void suspenderCancelacion() {

    }

    public void cancelar(View view){

        //Configuracion de Alerta para cancelar la reserva

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Esta seguro que desea cancelar la reserva de pista ?");
        dialogo1.setCancelable(false);

        Intent intent=new Intent(this, PantallaPrincipal.class);

        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptarSuspension();
                startActivity(intent);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                suspenderCancelacion();
            }
        });
        dialogo1.show();



    }

    public void confirmaReserva(View view){

        Intent intent = getIntent();

        // Obtén los datos de la reserva
        String usuario = SessionManager.getInstance().getCurrentUsuario();
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");

        int numPersonas = intent.getIntExtra("numPersonas", 0);

        // Insertar la reserva en la base de datos
        long resultadoInsercion = dbHelper.insertarReserva(usuario, fecha, hora, numPersonas);

        Log.d("DEBUG", "Resultado de la inserción: " + resultadoInsercion);

        if (resultadoInsercion != -1) {
            Toast.makeText(this, "Reserva guardada en la base de datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la reserva en la base de datos", Toast.LENGTH_SHORT).show();
        }

        // Finalmente, inicia la actividad de la pantalla principal
        Intent intentPantallaPrincipal = new Intent(this, PantallaPrincipal.class);
        startActivity(intentPantallaPrincipal);

    }
}