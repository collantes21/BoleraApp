package com.example.boleraapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

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
        int numPersonas = intent.getIntExtra("numPersonas", 0);



        textViewDate.setText("Fecha:    " + fecha);
        textViewHora.setText("Hora:   " + hora);
        textViewPersonas.setText("Num. Personas:        " + numPersonas);


        // Obtener y guardar el valor del identificador y el contador
        int contadorReservas;
        String identificadorReserva;

        SharedPreferences sharedPreferences = getSharedPreferences("Reservas", MODE_PRIVATE);
        contadorReservas = sharedPreferences.getInt("contador", 0);
        identificadorReserva = generarIdentificador(contadorReservas);

        // Almacenar datos en SharedPreferences
        guardarReserva(sharedPreferences, identificadorReserva, numPersonas);

        // Incrementar el contador y guardar el nuevo valor
        contadorReservas++;
        guardarContadorReservas(sharedPreferences, contadorReservas);

    }

    private String generarIdentificador(int contador) {
        // Formatear el contador como un identificador único
        return String.format(Locale.getDefault(), "%03d", contador);
    }

    private void guardarContadorReservas(SharedPreferences sharedPreferences, int contador) {
        // Almacenar el nuevo valor del contador en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("contador", contador);
        editor.apply();
    }

    private void guardarReserva(SharedPreferences sharedPreferences, String identificadorReserva, int numPersonas) {
        // Obtener un editor para realizar cambios
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Obtener el número total de reservas para este identificador
        int reservasAnteriores = sharedPreferences.getInt(identificadorReserva, 0);

        // Incrementar el número de reservas y almacenar el valor actualizado
        editor.putInt(identificadorReserva, reservasAnteriores + numPersonas);

        // Aplicar los cambios
        editor.apply();
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

        Toast.makeText(this, "Reserva guardada correctamente", Toast.LENGTH_LONG).show();

        Intent intent=new Intent(this, PantallaPrincipal.class);

        startActivity(intent);

    }
}