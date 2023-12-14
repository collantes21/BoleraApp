package com.example.boleraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PantallaReservaPista extends AppCompatActivity {


    private String[]paises={"Barcelona","Madrid","Bilbao","Vigo","Valladolid","Sevilla","Malaga","Alicante","Leon","Oviedo"};

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro_pista);
        listView=findViewById(R.id.listView);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,paises);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(PantallaReservaPista.this, PantallaReservaPista1.class);
                startActivity(intent);
            }
        });
    }
}