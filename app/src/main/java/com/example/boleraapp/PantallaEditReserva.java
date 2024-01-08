package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;

public class PantallaEditReserva extends AppCompatActivity {


    private DbHelper dbHelper;

    private EditText etHoraReserva, etFecha, etNumPersonas, etNumReserva;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_edit_reserva);

        etHoraReserva=findViewById(R.id.etHoraReserva);
        etFecha=findViewById(R.id.etFecha);
        etNumPersonas=findViewById(R.id.etNumPersonas);
        etNumReserva=findViewById(R.id.etNumReserva);
        etNumReserva.setEnabled(false);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);

        // Obtener los datos de la reserva del Intent
        Intent intent = getIntent();
        int numReserva = intent.getIntExtra("numReserva", -1);
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");
        int numPersonas = intent.getIntExtra("numPersonas", 0);

        // Mostrar los datos en los EditText
        etNumReserva.setText(String.valueOf(numReserva));
        etFecha.setText(fecha);
        etHoraReserva.setText(hora);
        etNumPersonas.setText(String.valueOf(numPersonas));

    }
    public void modificar(View view) {
        // Obtener los datos actuales de los EditText
        String fechaNueva = etFecha.getText().toString();
        String horaNueva = etHoraReserva.getText().toString();
        int numPersonasNueva = Integer.parseInt(etNumPersonas.getText().toString());
        int numReserva = Integer.parseInt(etNumReserva.getText().toString());

        // Modificar la reserva en la base de datos
        DbHelper dbHelper = new DbHelper(this);
        int rowsAffected = dbHelper.modificarReserva(numReserva, fechaNueva, horaNueva, numPersonasNueva);

        if (rowsAffected > 0) {

            Toast.makeText(this, "Reserva modificada correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(PantallaEditReserva.this, PantallaHistorial.class);

            startActivity(intent);

        } else {

            Toast.makeText(this, "No se pudo modificar su reserva. Pruebe de nuevo mas tarde", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(PantallaEditReserva.this, PantallaHistorial.class);

            startActivity(intent);
        }
    }

    public void borrar(View view) {
        // Obtener el número de reserva
        int numReserva = Integer.parseInt(etNumReserva.getText().toString());

        // Borrar la reserva de la base de datos
        DbHelper dbHelper = new DbHelper(this);
        int rowsAffected = dbHelper.borrarReserva(numReserva);

        if (rowsAffected > 0) {

            Toast.makeText(this, "Reserva borrada correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(PantallaEditReserva.this, PantallaHistorial.class);

            startActivity(intent);

        } else {

            Toast.makeText(this, "Error borrando la reserva, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(PantallaEditReserva.this, PantallaHistorial.class);

            startActivity(intent);
        }
    }

    public void salir(View view){

        Intent intent = new Intent(PantallaEditReserva.this, PantallaHistorial.class);

        startActivity(intent);
    }
}