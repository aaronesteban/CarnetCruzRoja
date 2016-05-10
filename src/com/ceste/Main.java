package com.ceste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);
    static ArrayList<CarnetCruzRoja> carnetList = new ArrayList<>();

    public static void main(String[] args) {

        //Deserializacion
        CarnetsCruzRojaDb serialize = new CarnetsCruzRojaDb("datos.ser");
        serialize.setCarnets(carnetList);
        try {
            carnetList=serialize.cargar();
        } catch (FileNotFoundException e) {
            System.out.printf("Erro al leer el fichero");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printCarnetFromArrayList(carnetList);

        //Objects to ArrayList
        carnetList.addAll(addCarnet());
        printCarnetFromArrayList(carnetList);

        /*//CompareTo Apellidos
        System.out.println("\nImprimo carnets ordenados por apellido");
        Collections.sort(carnetList);
        printCarnetFromArrayList(carnetList);

        //Compare Dni
        System.out.println("\nImprimo carnets ordenados por DNI");
        Collections.sort(carnetList, new OrdenaCarnets("dni"));
        printCarnetFromArrayList(carnetList);

        //Compare Date
        System.out.println("\nImprimo carnets ordenados por Fecha");
        Collections.sort(carnetList, new OrdenaCarnets("fecha"));
        printCarnetFromArrayList(carnetList);*/

        //Serialización
        try {
            serialize.guardar();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<CarnetCruzRoja> addCarnet() {
        ArrayList<CarnetCruzRoja> carnet = new ArrayList<>();
        boolean opcion = true;
        char salida;
        int i = 0;
        do {
            if (i== 0) System.out.println("\nPresione una tecla para introducir el carnet:");
            leer.nextLine(); //LIMPIO EL BUFFER PARA QUE NO SE SALTE CAMPOS
            carnet.add(new CarnetCruzRoja(pideDatos("el D.N.I:")));
            carnet.get(i).setNombre(pideDatos("el nombre:"));
            carnet.get(i).setApellidos(pideDatos("los apellidos:"));
            carnet.get(i).setProvincia(pideDatos("la provincia:"));
            carnet.get(i).setLocalidad(pideDatos("la localidad:"));
            carnet.get(i).setServicio(pideDatos("el servicio:"));

            do {
                try {
                    carnet.get(i).setFecha(pideDatos("la fecha (d/m/y):"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: No ha introducido la fecha en el formato correcto.");
                }
            } while (carnet.get(i).getFecha() == null);

            do {
                System.out.println("\n¿Desea introducir otro carnet? <y/n>");
                salida = leer.next().charAt(0);
                if (salida == 'n') opcion = false;
                else if (salida != 'n' && salida != 'y') System.out.println("\nError: La opción intoducida no es válida");
            }while (salida != 'n' && salida != 'y');

            ++i;
        }while (opcion);
        return carnet;
    }

    private static void printCarnetFromArrayList(ArrayList<CarnetCruzRoja> carnetList) {
        System.out.println("\n****** Imprimo el ArrayList ******");
        for (CarnetCruzRoja aCarnetList : carnetList) {
            System.out.println(aCarnetList);
        }
    }

    private static String pideDatos(String campo){
        String resultado;
        do {
        System.out.println("\nIntroduce " + campo);
            resultado = leer.nextLine();
            if (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):")) System.out.println("Error: Debe introducir " + campo);
        } while (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):"));
        return resultado;
    }
}
