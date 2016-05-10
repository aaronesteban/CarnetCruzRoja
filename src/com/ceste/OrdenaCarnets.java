package com.ceste;

import java.util.Comparator;

public class OrdenaCarnets implements Comparator<CarnetCruzRoja> {
    private String opcion = "";

    public OrdenaCarnets(String opcion) {
        this.opcion=opcion;
    }

    @Override
    public int compare(CarnetCruzRoja o1, CarnetCruzRoja o2) {
        switch (opcion) {
            case "dni":
                return o1.getDni().compareTo(o2.getDni());
            case "fecha":
                return o1.getFecha().compareTo(o2.getFecha());
            case "provincia":
                return o1.getProvincia().compareTo(o2.getProvincia());

        }
        return 0;
    }
}
