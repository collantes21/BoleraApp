package com.example.boleraapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PantallaReservaPista1 extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btHora;
    private EditText etHora, numPersonas;

    private int hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reserva_pista1);

        calendarView = findViewById(R.id.calendarView);
        btHora = findViewById(R.id.btHora);
        numPersonas=findViewById(R.id.numPersonas);
        etHora = findViewById(R.id.etHora);
        etHora.setEnabled(false);

        btHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                establecerFecha(view);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Obtener la fecha seleccionada como un string (puedes ajustar el formato según tus necesidades)
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                // Guardar la fecha seleccionada en SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedDate", selectedDate);
                editor.apply();
            }
        });


    }

    // Método para establecer la hora con el TimePickerDialog
    public void establecerFecha(View view) {
        if (view == btHora) {
            final Calendar horario = Calendar.getInstance();
            hora = horario.get(Calendar.HOUR_OF_DAY);
            minuto = horario.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            // Actualizar el EditText con la hora seleccionada
                            etHora.setText(String.format("          %02d:%02d", i, i1));
                        }
                    }, hora, minuto, false);

            // Agregado: Mostrar el diálogo de selección de tiempo
            timePickerDialog.show();
        }
    }

    public void continuar(View view) {

        // Recuperar la fecha seleccionada desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String selectedDate = preferences.getString("selectedDate", "No date selected");

        // Recuperar la hora seleccionada desde el EditText
        String selectedHour = etHora.getText().toString();

        // Recuperar el número de personas desde el EditText
        String selectedNumPersonas = numPersonas.getText().toString();

        if (selectedDate.isEmpty() || selectedHour.isEmpty() || selectedNumPersonas.isEmpty()) {
            // Mostrar Toast indicando que los datos son incorrectos
            Toast.makeText(this, "Por favor, selecciona fecha, hora y número de personas", Toast.LENGTH_SHORT).show();

            // Resetear los campos
            etHora.setText("");
            numPersonas.setText("");

            return; // Salir del método ya que no se cumplen las condiciones
        }

        // Crear un intent y pasar los datos a la siguiente actividad
        Intent intent = new Intent(this, PantallaReservaPistaConfirma.class);
        intent.putExtra("fecha", selectedDate);
        intent.putExtra("hora", selectedHour);
        // Convertir la cantidad de personas a entero antes de ponerla en el intent
        int personas = Integer.parseInt(selectedNumPersonas);
        intent.putExtra("numPersonas", personas);


        startActivity(intent);
    }

}
