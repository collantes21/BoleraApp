package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.boleraapp.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registro(View view){

        Intent intent= new Intent(MainActivity.this, PantallaRegistro.class);

        startActivity(intent);

        DbHelper dbHelper=new DbHelper(MainActivity.this);

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        if (db!=null){
            Toast notificacion=Toast.makeText(this,"BBDD creada correctamente",Toast.LENGTH_LONG);
            notificacion.show();
        } else {
            Toast notificacion=Toast.makeText(this,"BBDD no pudo ser creada",Toast.LENGTH_LONG);
            notificacion.show();
        }
    }

    public void login(View view){

        Intent intent= new Intent(MainActivity.this, PantallaLogIn.class);

        startActivity(intent);
    }
}