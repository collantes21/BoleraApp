package com.example.boleraapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bolera.db";
    public static final String TABLE_USERS = "t_usuarios"; // Agregado para acceder desde PantallaRegistro

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "usuario VARCHAR(35) PRIMARY KEY," +
                "contrasena VARCHAR(35)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    // Método para insertar un nuevo usuario
    public long insertarUsuario(String usuario, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("contrasena", contrasena);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // Método para verificar si un usuario y contraseña existen
    public boolean verificarCredenciales(String usuario, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Consulta para verificar credenciales
            String consulta = "SELECT usuario FROM " + TABLE_USERS + " WHERE usuario = ? AND contrasena = ?";
            cursor = db.rawQuery(consulta, new String[]{usuario, contrasena});

            // Verificar si se encontró algún resultado
            return cursor.getCount() > 0;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
}


