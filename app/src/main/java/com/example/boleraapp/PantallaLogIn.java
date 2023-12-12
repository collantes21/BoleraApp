package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PantallaLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_log_in);
    }

    public void entrar(View view){

        Intent intent= new Intent(PantallaLogIn.this, PantallaPrincipal.class);

        startActivity(intent);
    }
}