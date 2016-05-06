package com.ceste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Introduce cuantos carnet quieres añadir:");
        int size = leer.nextInt();

        //Objects to Array
        CarnetCruzRoja carnet [] = new CarnetCruzRoja[size];
        carnet = addCarnet(carnet);
        printCarnetFromArray(carnet);

        //Objects to ArrayList
        ArrayList<CarnetCruzRoja> carnetList = new ArrayList<>();
        carnetList = copyCarnetToArrayList(carnet, carnetList);
        printCarnetFromArrayList(carnetList);

        //Objects to HashSet
        HashSet<CarnetCruzRoja> carnetSet = new HashSet<>();
        carnetSet = copyCarnetToHashSet(carnet, carnetSet);
        printCarnetFromHashSet(carnetSet);

        //CompareTo
        carnet=comparaApellido(carnet);
        printCarnetFromArray(carnet);

        //Compare
        

        //Guardar carnets
        ExportaCarnets2CSV exportaCarnets2CVS = new ExportaCarnets2CSV(carnetList, "/Users/Aaron/Desktop/objetos.txt");
        exportaCarnets2CVS.guardarDatos();
    }

    private static CarnetCruzRoja[] comparaApellido(CarnetCruzRoja[] carnet) {
        for (int i=0; i< carnet.length; ++i){
            for (int j=i+1; j< carnet.length; ++j){
                if (carnet[i].compareTo(carnet[j]) >0){
                    CarnetCruzRoja auxiliar;
                    auxiliar = carnet[i];
                    carnet[i] = carnet [j];
                    carnet [j] = auxiliar;
                }
            }
        }
        return carnet;
    }

    public static CarnetCruzRoja[] addCarnet(CarnetCruzRoja carnet[]){
        for(int i=0; i<carnet.length; ++i){
            System.out.println("\nIntroduce el D.N.I:");
            carnet[i] = new CarnetCruzRoja(leer.nextLine());
            leer.nextLine();
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
            System.out.println("\nIntroduce la fecha:");
            carnet[i].setFecha(leer.nextLine());
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
