package com.ceste;

import java.util.Comparator;

public class OrdenaCarnets implements Comparator<CarnetCruzRoja> {
    private String opcion = "";

    public OrdenaCarnets(String opcion) {
        this.opcion=opcion;
    }
    @Override
    public int compare(CarnetCruzRoja o1, CarnetCruzRoja o2) {
        if (opcion.equals("dni")) return o1.getDni().compareTo(o2.getDni());
        else if (opcion.equals("fecha")) return o1.getFecha().compareTo(o2.getFecha());
        return 0;
    }
}
