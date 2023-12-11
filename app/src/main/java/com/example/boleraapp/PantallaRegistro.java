package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PantallaRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
    }

    public void registrar(View view){

        Toast notificacion=Toast.makeText(this,"Usuario registrado correctamente",Toast.LENGTH_LONG);
        notificacion.show();
    }
}