package com.ceste;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Aaron on 9/5/16.
 */
public class CarnetsCruzRojaDb {
    private ArrayList<CarnetCruzRoja> carnets;
    private String fichero;

    CarnetsCruzRojaDb(String fichero) {
        this.fichero=fichero;
    }

    public void setCarnets(ArrayList carnets) {
        this.carnets = carnets;
    }

    /**
     * Abre el fichero en modo lectura y carga sus datos en la variable carnets
     * Debes usar flujos de objetos (ObjectInputStream)
     */
    public void cargar() throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(fichero);
        ObjectInputStream s = new ObjectInputStream(f);

        carnets = (ArrayList<CarnetCruzRoja>) s.readObject();

        s.close();

        System.out.println("\nDatos Deserializados:\n");
        System.out.println(carnets.toString());
    }

    /**
     * Abre el fichero en modo escritura y vuelca en el contenido del atributo carnets
     * Debes usar flujos de objetos (ObjectOutputStream)
     */
    public void guardar() {
        try {
            FileOutputStream f =
                    new FileOutputStream(fichero, true);
            ObjectOutputStream s =
                    new ObjectOutputStream(f);
            s.writeObject(carnets);

            s.flush();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un carnet a la colección (al añadir debe guardar)
     *
     * @param carnet añadir
     */
    // No he añadido el motodo add carnet porque ya lo tengo implementado en el main.

    /**
     *
     * @param indice del carnet a coger
     * @return CarnetCruzRoja seleccionado
     */
    public ArrayList<CarnetCruzRoja> get(int indice) {
        try {
            FileOutputStream f =
                    new FileOutputStream("data.ser");
            ObjectOutputStream s =
                    new ObjectOutputStream(f);
            for (int i=0; i<carnets.size(); ++i){
                s.writeObject(carnets.get(indice));
            }
            s.flush();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carnets;
    }

}
