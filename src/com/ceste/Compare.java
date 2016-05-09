package com.ceste;

import org.joda.time.DateTime;

/**
 * Created by Aaron on 9/5/16.
 */
public class Compare {
    public int compare(CarnetCruzRoja o1, CarnetCruzRoja o2) {
        return o1.getDni().compareTo(o2.getDni());
    }

    public int compare(DateTime fecha, DateTime fecha2) {
        return fecha.compareTo(fecha2);
    }
}
