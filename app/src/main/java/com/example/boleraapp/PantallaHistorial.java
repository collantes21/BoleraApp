package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;

import java.util.ArrayList;

public class PantallaHistorial extends AppCompatActivity {

    private ListView listViewReservas;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_historial);

        listViewReservas = findViewById(R.id.listViewReservas);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);

        // Obtener todas las reservas del usuario actual
        String usuarioActual = SessionManager.getInstance().getCurrentUsuario();
        Cursor cursorReservas = dbHelper.obtenerReservasUsuario(usuarioActual);

        // Crear una lista de cadenas para almacenar la información de las reservas
        ArrayList<String> listaReservas = new ArrayList<>();

        // Iterar a través del cursor para obtener los datos de cada reserva
        if (cursorReservas.moveToFirst()) {
            do {
                int numReserva = cursorReservas.getInt(cursorReservas.getColumnIndex("numReserva"));
                String fecha = cursorReservas.getString(cursorReservas.getColumnIndex("fecha"));
                String hora = cursorReservas.getString(cursorReservas.getColumnIndex("hora"));
                int numPersonas = cursorReservas.getInt(cursorReservas.getColumnIndex("numPersonas"));

                // Crear una cadena con la información de la reserva y agregarla a la lista
                String infoReserva = String.format("Reserva %d   -   Fecha: %s,                         Hora:%s, Personas: %d", numReserva, fecha, hora, numPersonas);
                listaReservas.add(infoReserva);

            } while (cursorReservas.moveToNext());
        }

        // Cerrar el cursor
        cursorReservas.close();

        // Crear un adaptador para la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaReservas);

        // Establecer el adaptador en el ListView
        listViewReservas.setAdapter(adapter);

        // Configurar un listener para manejar la selección de un elemento de la lista
        listViewReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el texto del elemento seleccionado
                String selectedReserva = (String) parent.getItemAtPosition(position);

                // Mostrar un mensaje de prueba (puedes cambiar esto por abrir otra actividad)
                Toast.makeText(getApplicationContext(), "Reserva seleccionada: " + selectedReserva, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
