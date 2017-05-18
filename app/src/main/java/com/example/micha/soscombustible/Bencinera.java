package com.example.micha.soscombustible;

/**
 * Created by micha on 15-05-2017.
 */

public class Bencinera {
    private int id;
    private String nombre;
    private String direccion;
    private int logo;

    public Bencinera(int code, String name, String address, int logo){
        this.setId(code);
        this.setNombre(name);
        this.setDireccion(address);
        this.setLogo(logo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
