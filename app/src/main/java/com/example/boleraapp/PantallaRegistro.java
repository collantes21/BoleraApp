package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;


public class PantallaRegistro extends AppCompatActivity {

    private DbHelper dbHelper;
    private EditText user, pass1, pass2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        user=findViewById(R.id.user);
        pass1=findViewById(R.id.pass1);
        pass2=findViewById(R.id.pass2);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);
    }

    public boolean usuarioExiste(String usuario) {
        SQLiteDatabase db = this.getReadeableDa();
        Cursor cursor = null;

        try {
            // Consulta para buscar el usuario en la tabla
            String consulta = "SELECT usuario FROM " + TABLE_USERS + " WHERE usuario = ?";
            cursor = db.rawQuery(consulta, new String[]{usuario});

            // Verificar si se encontró algún resultado
            return cursor.getCount() > 0;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public void registrar(View view){

        String usuario=user.getText().toString();
        String password=pass1.getText().toString();
        String passwordComprueba=pass2.getText().toString();


    }
}