package com.example.boleraapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bolera.db";
    public static final String TABLE_USERS = "t_usuarios"; // Agregado para acceder desde PantallaRegistro
    public static final String TABLE_RESERVAS = "t_reservas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "usuario VARCHAR(35) PRIMARY KEY," +
                "contrasena VARCHAR(35)" +
                ")");

        Log.d("DbHelper", "onCreate called");
        // Crear la tabla de reservas
        db.execSQL("CREATE TABLE " + TABLE_RESERVAS + "(" +
                "numReserva INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario VARCHAR(35)," +
                "fecha VARCHAR(20)," +
                "hora VARCHAR(10)," +
                "numPersonas INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVAS);
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

    // Método para insertar una nueva reserva asociada a un usuario
    public long insertarReserva(String usuario, String fecha, String hora, int numPersonas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("numPersonas", numPersonas);
        long result = db.insert(TABLE_RESERVAS, null, values);
        db.close();
        return result;
    }

    // Método para obtener todas las reservas de un usuario
    public Cursor obtenerReservasUsuario(String usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RESERVAS, null, "usuario=?", new String[]{usuario}, null, null, null);
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

    // Método para modificar una reserva por número de reserva
    public int modificarReserva(int numReserva, String fecha, String hora, int numPersonas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("numPersonas", numPersonas);

        int rowsAffected = db.update(TABLE_RESERVAS, values, "numReserva=?", new String[]{String.valueOf(numReserva)});
        db.close();

        return rowsAffected;
    }

    // Método para borrar una reserva por número de reserva
    public int borrarReserva(int numReserva) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_RESERVAS, "numReserva=?", new String[]{String.valueOf(numReserva)});
        db.close();

        return rowsAffected;
    }
}


