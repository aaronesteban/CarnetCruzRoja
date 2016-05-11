package com.ceste;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarnetCruzRoja implements Comparable<CarnetCruzRoja>, Serializable{
    private String dni = "";
    private String apellidos = "";
    private String nombre = "";
    private String provincia = "";
    private String localidad = "";
    private String servicio = "";
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public CarnetCruzRoja() {

    }

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
    public Date getFecha() {
        return date;
    }

    //SETTERS

    public void setDni(String dni) {
        this.dni = dni;
    }
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
    public void setFecha(String fecha) throws ParseException {
        this.date = dateFormat.parse(fecha);

    }

    @Override
    public String toString() {
        return nombre + "\t" + apellidos + "\t" + dni + "\t" + provincia + "\t" + localidad + "\t"
                + servicio + "\t" + dateFormat.format(date);
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
}
