package com.ceste;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aaron on 5/5/16.
 */
public class ExportaCarnets2CSV {
    private ArrayList<CarnetCruzRoja> carnets;
    private String nombreFichero;

    /**
     * @param carnets Colección de carnets a guardar
     * @param nombreFichero nombre del fichero donde guardarlos
     */
    public ExportaCarnets2CSV(ArrayList<CarnetCruzRoja> carnets, String nombreFichero) {
        this.carnets=carnets;
        this.nombreFichero=nombreFichero;
    }

    public void guardarDatos() {
        try{
            FileWriter escritor = new FileWriter(nombreFichero, true);
            BufferedWriter escritorConBuffer= new BufferedWriter(escritor);
            for (int i=0; i<carnets.size(); ++i){
                String carnetEnString = carnets.get(i).toString();
                escritorConBuffer.write(carnetEnString, 0, carnetEnString.length());
                escritorConBuffer.newLine();
            }
            escritorConBuffer.flush();
            escritorConBuffer.close();
        }catch (IOException e){
            System.out.println("Error de lectura o escritura" + e);
        }

    }
}
