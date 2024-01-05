package com.example.boleraapp;

public class SessionManager {

    private static SessionManager instance;
    private String currentUsuario;

    private SessionManager() {
        // Constructor privado para implementar el patrón Singleton
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getCurrentUsuario() {
        return currentUsuario;
    }

    public void setCurrentUsuario(String usuario) {
        this.currentUsuario = usuario;
    }
}
