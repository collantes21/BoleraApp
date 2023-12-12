package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;

public class PantallaLogIn extends AppCompatActivity {

    private DbHelper dbHelper;
    private EditText userVerifica, passVerifica;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_log_in);
        userVerifica=findViewById(R.id.userVerifica);
        passVerifica=findViewById(R.id.passVerifica);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);
    }

    public void entrar(View view){

        String usuario=userVerifica.getText().toString();

        String contrasena=passVerifica.getText().toString();

        if (dbHelper.verificarCredenciales(usuario, contrasena)){

            Intent intent= new Intent(PantallaLogIn.this, PantallaPrincipal.class);

            startActivity(intent);
        } else {

            Toast.makeText(this, "Usuario o contraseña incorrecto.", Toast.LENGTH_LONG).show();

            userVerifica.setText("");
            passVerifica.setText("");
        }

    }

    public void atras(View view){

        Intent i=new Intent(this, MainActivity.class);

        startActivity(i);
    }
}