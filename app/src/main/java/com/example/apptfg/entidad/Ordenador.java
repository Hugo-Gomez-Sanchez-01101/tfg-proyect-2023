package com.example.apptfg.entidad;

public class Ordenador {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ordenador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
