package com.ceste;

import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce cuantos carnet quieres añadir:");
        int size = leer.nextInt();
        CarnetCruzRoja carnet [] = new CarnetCruzRoja[size];
        carnet = addCarnet(size, carnet);
        
    }

    public static CarnetCruzRoja[] addCarnet(int size, CarnetCruzRoja carnet[]){

        for(int i=0; i<size; ++i){
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



}
