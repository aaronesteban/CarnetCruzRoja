package com.ceste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

        //CompareTo Apellidos
        System.out.println("\nImprimo carnets ordenados por apellido");
        Collections.sort(carnetList);
        printCarnetFromArrayList(carnetList);

        /*//Compare Dni
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
            leer.nextLine();
            System.out.println("\nIntroduce el D.N.I:");
            carnet.add(new CarnetCruzRoja(leer.nextLine()));
            System.out.println("\nIntroduce el nombre:");
            carnet.get(i).setNombre(leer.nextLine());
            System.out.println("\nIntroduce los apellidos:");
            carnet.get(i).setApellidos(leer.nextLine());
            System.out.println("\nIntroduce la provincia:");
            carnet.get(i).setProvincia(leer.nextLine());
            System.out.println("\nIntroduce la localidad:");
            carnet.get(i).setLocalidad(leer.nextLine());
            System.out.println("\nIntroduce el servicio:");
            carnet.get(i).setServicio(leer.nextLine());
            do {
                System.out.println("\nIntroduce la fecha (d/m/y):");
                try {
                    carnet.get(i).setFecha(leer.nextLine());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: No ha introducido la fecha en el formato correcto.");

                }
            } while (carnet.get(i).getFecha() == null);
            ++i;
            do {
                System.out.println("¿Desea introducir otro carnet? <y/n>");
                salida = leer.next().charAt(0);
                if (salida == 'n') opcion = false;
                else if (salida != 'n' && salida != 'y') System.out.println("Error: La opción intoducida no es válida");
            }while (salida != 'n' && salida != 'y');
        }while (opcion == true);
        return carnet;
    }

    private static void printCarnetFromArrayList(ArrayList<CarnetCruzRoja> carnetList) {
        System.out.println("\n****** Imprimo el ArrayList ******");
        for (int i=0; i<carnetList.size(); ++i){
            System.out.println(carnetList.get(i));
        }
    }

}
