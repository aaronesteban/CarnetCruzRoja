package com.ceste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce cuantos carnet quieres añadir:");
        int size = leer.nextInt();

        //Objects to Array
        CarnetCruzRoja carnet [] = new CarnetCruzRoja[size];
        carnet = addCarnet(carnet);
        printCarnetFromArray(carnet);

        //Objects to ArrayList
        ArrayList<CarnetCruzRoja> carnetList = new ArrayList<CarnetCruzRoja>();
        carnetList = copyCarnetToArrayList(carnet, carnetList);
        printCarnetFromArrayList(carnetList);

        //Objects to HashSet
        HashSet<CarnetCruzRoja> carnetSet = new HashSet<CarnetCruzRoja>();
        carnetSet = copyCarnetToHashSet(carnet, carnetSet);
        printCarnetFromHashSet(carnetSet);

        //CompareTo Apellidos
        System.out.println("\nImprimo carnets ordenados por apellido");
        carnet=compara(carnet, "apellido");
        printCarnetFromArray(carnet);

        //Compare Dni
        System.out.println("\nImprimo carnets ordenados por DNI");
        carnet=compara(carnet, "dni");
        printCarnetFromArray(carnet);

        //Compare Date
        System.out.println("\nImprimo carnets ordenados por Fecha");
        carnet=compara(carnet, "fecha");
        printCarnetFromArray(carnet);

        //Guardar carnets
        ExportaCarnets2CSV exportaCarnets2CVS = new ExportaCarnets2CSV(carnetList, "objetos.csv");
        exportaCarnets2CVS.guardarDatos();

        //Serialización
        CarnetsCruzRojaDb serialize = new CarnetsCruzRojaDb("datos.ser");
        serialize.setCarnets(carnetList);
        serialize.guardar();

        //Deserializacion
        try {
            serialize.cargar();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Guarda carnets serializando uno a uno
        for (int i=0; i<carnetList.size(); ++i){
            serialize.get(i);
        }

    }

    private static CarnetCruzRoja[] compara(CarnetCruzRoja[] carnet, String opcion) {
        Compare comparador = new Compare();
        for (int i=0; i< carnet.length; ++i){
            for (int j=i+1; j< carnet.length; ++j){
                if (opcion == "fecha" && comparador.compare(carnet[i].getFecha(), carnet[j].getFecha()) > 0){
                    CarnetCruzRoja auxiliar;
                    auxiliar = carnet[i];
                    carnet[i] = carnet [j];
                    carnet [j] = auxiliar;
                }
                else if (opcion == "dni" && comparador.compare(carnet[i], carnet[j]) >0){
                    CarnetCruzRoja auxiliar;
                    auxiliar = carnet[i];
                    carnet[i] = carnet [j];
                    carnet [j] = auxiliar;
                }
                else if (opcion == "apellido" && carnet[i].compareTo(carnet[j]) >0){
                    CarnetCruzRoja auxiliar;
                    auxiliar = carnet[i];
                    carnet[i] = carnet [j];
                    carnet [j] = auxiliar;
                }
            }
        }
        return carnet;
    }

    public static CarnetCruzRoja[] addCarnet(CarnetCruzRoja carnet[]) {
        for(int i=0; i<carnet.length; ++i){
            boolean opcion = false;
            leer.nextLine();
            while(opcion == false){
                System.out.println("\nIntroduce el D.N.I:");
                carnet[i] = new CarnetCruzRoja(leer.nextLine());
                if (carnet[i].getDni().isEmpty()){
                    System.out.println("Error: Debe de introducir el DNI.");
                    continue;
                }
                else opcion = true;
            }
            opcion = false;
            System.out.println("\nIntroduce el nombre:");
            carnet[i].setNombre(leer.nextLine());
            System.out.println("\nIntroduce los apellidos:");
            carnet[i].setApellidos(leer.nextLine());
            System.out.println("\nIntroduce la provincia:");
            carnet[i].setProvincia(leer.nextLine());
            System.out.println("\nIntroduce la localidad:");
            carnet[i].setLocalidad(leer.nextLine());
            System.out.println("\nIntroduce el servicio:");
            carnet[i].setServicio(leer.nextLine());
            while(opcion == false){
                System.out.println("\nIntroduce la fecha (d/m/y):");
                try{
                    carnet[i].setFecha(leer.nextLine());
                    opcion = true;
                }catch (IllegalArgumentException e){
                    System.out.println("Error: No ha introducido la fecha en el formato correcto.");
                    continue;
                }
            }
        }
        return carnet;
    }

    public static void printCarnetFromArray(CarnetCruzRoja carnet[]){
        System.out.println("\n****** Imprimo el Array ******");
        for (int i=0; i<carnet.length; ++i){
            System.out.println(carnet[i].toString());
        }
    }

    private static ArrayList<CarnetCruzRoja> copyCarnetToArrayList(CarnetCruzRoja[] carnet, ArrayList<CarnetCruzRoja> carnetList) {
        for (int i=0; i<carnet.length; ++i){
            carnetList.add(carnet[i]);
        }
        //carnetList.add(carnet[1]); Duplica el objeto dentro del ArrayList
        return carnetList;
    }

    private static void printCarnetFromArrayList(ArrayList<CarnetCruzRoja> carnetList) {
        System.out.println("\n****** Imprimo el ArrayList ******");
        for (int i=0; i<carnetList.size(); ++i){
            System.out.println(carnetList.get(i));
        }
    }

    private static HashSet<CarnetCruzRoja> copyCarnetToHashSet(CarnetCruzRoja[] carnet, HashSet<CarnetCruzRoja> carnetSet) {
        for (int i=0; i<carnet.length; ++i){
            carnetSet.add(carnet[i]);
        }
        //carnetSet.add(carnet[1]); Remplaza el objeto dentro del HashSet
        return carnetSet;
    }

    private static void printCarnetFromHashSet(HashSet<CarnetCruzRoja> carnetSet) {
        System.out.println("\n****** Imprimo el HashSet ******");
        for (CarnetCruzRoja item:carnetSet){
            System.out.println(item);
        }

        /*for(Iterator<CarnetCruzRoja> iter = carnetSet.iterator(); iter.hasNext();)
        {
            CarnetCruzRoja element = iter.next();
            System.out.println(element);
        }*/
    }

}
