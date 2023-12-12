package com.example.boleraapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="bolera.db";
    private static final String TABLA_USUARIOS="t_usuarios";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLA_USUARIOS + "(" + "usuario varchar (35) primary key,"
                + "contraseña varchar (35)"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLA_USUARIOS);
        onCreate(db);
    }
}


