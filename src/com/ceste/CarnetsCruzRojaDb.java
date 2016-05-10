package com.ceste;

import java.io.*;
import java.util.ArrayList;

public class CarnetsCruzRojaDb {
    private ArrayList<CarnetCruzRoja> carnets;
    private String fichero;

    CarnetsCruzRojaDb(String fichero) {
        this.fichero=fichero;
    }

    public void setCarnets(ArrayList<CarnetCruzRoja> carnets) {
        this.carnets = carnets;
    }

    /**
     * Abre el fichero en modo lectura y carga sus datos en la variable carnets
     * Debes usar flujos de objetos (ObjectInputStream)
     */
    public ArrayList<CarnetCruzRoja> cargar() throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(fichero);
        ObjectInputStream s = new ObjectInputStream(f);
        carnets = (ArrayList<CarnetCruzRoja>) s.readObject();
        s.close();
        return carnets;
    }

    /**
     * Abre el fichero en modo escritura y vuelca en el contenido del atributo carnets
     * Debes usar flujos de objetos (ObjectOutputStream)
     */
    public void guardar() throws IOException {
        FileOutputStream fos = new FileOutputStream(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(carnets);
        oos.flush();
        oos.close();
    }

}
