package com.ceste;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce cuantos carnet quieres añadir:");
        int size = leer.nextInt();

        //Objects to Array
        CarnetCruzRoja carnet [] = new CarnetCruzRoja[size];
        carnet = addCarnet(carnet);
        printCarnet(carnet);

        //Objects to ArrayList
        ArrayList<CarnetCruzRoja> carnetList = new ArrayList<CarnetCruzRoja>();
        carnetList = copyCarnet(carnet, carnetList);
        printCarnetList(carnetList);
    }

    public static CarnetCruzRoja[] addCarnet(CarnetCruzRoja carnet[]){

        for(int i=0; i<carnet.length; ++i){
            carnet[i] = new CarnetCruzRoja();
            leer.nextLine();
            System.out.println("\nIntroduce el nombre:");
            carnet[i].setNombre(leer.nextLine());
            System.out.println("\nIntroduce los apellidos:");
            carnet[i].setApellidos(leer.nextLine());
            System.out.println("\nIntroduce el D.N.I:");
            carnet[i].setDni(leer.nextLine());
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

    public static void printCarnet(CarnetCruzRoja carnet[]){
        for (int i=0; i<carnet.length; ++i){
            System.out.println(carnet[i].toString());

        }
    }

    private static ArrayList<CarnetCruzRoja> copyCarnet(CarnetCruzRoja[] carnet, ArrayList<CarnetCruzRoja> carnetList) {
        for (int i=0; i<carnet.length; ++i){
            carnetList.add(carnet[i]);
        }
        return carnetList;
    }

    private static void printCarnetList(ArrayList<CarnetCruzRoja> carnetList) {
        for (int i=0; i<carnetList.size(); ++i){
            System.out.println(carnetList.get(i));
        }
    }

}
