package com.ceste;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Comparator;

/**
 * Created by Aaron on 25/4/16.
 */

public class CarnetCruzRoja implements Comparable<CarnetCruzRoja>, Comparator<CarnetCruzRoja>{
    private String dni = "";
    private String apellidos = "";
    private String nombre = "";
    private String provincia = "";
    private String localidad = "";
    private String servicio = "";
    private LocalDate date;

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
    public LocalDate getFecha() {
        return date;
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
        this.date = LocalDate.parse(fecha,
                DateTimeFormat.forPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        String carnet =nombre + "\t" + apellidos + "\t" + dni + "\t" + provincia + "\t" + localidad + "\t"
                + servicio + "\t" + date.toString("dd-MM-yyyy");
        return carnet;
    }

    @Override
    public int compareTo(CarnetCruzRoja o) {
        if (getApellidos().compareTo(o.getApellidos()) < 0) return -1;
        else if (getApellidos().compareTo(o.getApellidos()) > 0) return 1;
        else {
            if (getNombre().compareTo(o.getNombre()) < 0) return -1;
            else if (getNombre().compareTo(o.getNombre()) > 0) return 1;
        }
        return 0;
    }

    @Override
    public int compare(CarnetCruzRoja o1, CarnetCruzRoja o2) {
        return o1.getDni().compareTo(o2.getDni());
    }

}
