package com.ceste;

/**
 * Created by Aaron on 25/4/16.
 */

public class CarnetCruzRoja {
    private String dni = "";
    private String apellidos = "";
    private String nombre = "";
    private String provincia = "";
    private String localidad = "";
    private String servicio = "";
    private String fecha = "";

    public CarnetCruzRoja(String dni) {
        this.dni = dni;
    }

    //GETTERS
    public String getDni() {
        return dni;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getProvincia() {
        return provincia;
    }
    public String getLocalidad() {
        return localidad;
    }
    public String getServicio() {
        return servicio;
    }
    public String getFecha() {
        return fecha;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String carnet ="\nNombre: "+nombre + "\nApellidos: " + apellidos + "\nD.N.I: " + dni +
                "\nProvincia: " + provincia + "\nLocalidad: " + localidad + "\nServicio: " + servicio +
                "\nFecha: " + fecha;

        return carnet;
    }
}
