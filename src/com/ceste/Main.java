package com.ceste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);
    static ArrayList<CarnetCruzRoja> carnetList = new ArrayList<>();
    static CarnetsCruzRojaDb serialize = new CarnetsCruzRojaDb("datos.ser");

    public static void main(String[] args) {

        //Deserializacion
        serialize.setCarnets(carnetList);
        try {
            carnetList=serialize.cargar();
        } catch (FileNotFoundException e) {
            System.out.printf("Error al leer el fichero\n");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        menu();

    }

    private static void menu() {
        int select = 0;

        do {
            clearConsole();
            System.out.println("    ***********************************************************************");
            System.out.println("    *************** PRÁCTICA FINAL CARNETS BY AARON ESTEBAN ***************");
            System.out.println("    ***********************************************************************");

            if (select > 5) System.out.println("\n\n\t--> Error: Opción no válida");

            System.out.println("\n\n\tElige opción:\n\n" +
                    "\t1.- Añadir carnets\n" +
                    "\t2.- Listar carnets\n" +
                    "\t3.- Ordenar carnets\n" +
                    "\t4.- Dividir\n" +
                    "\t5.- Eliminar carnets\n" +
                    "\t0.- Salir\n");
            try {
                select = leer.nextInt();
            }catch (InputMismatchException e){
                System.out.println("\n\t--> Error: No ha introducido un carácter válido.\n");
                break;
            }
            switch(select){
                case 1:
                    //Objects to ArrayList
                    carnetList.addAll(addCarnets());
                    break;
                case 2:
                    //Print ArrayList
                    printCarnets(carnetList);
                    break;
                case 3:
                    ordenaCarnets();
                    break;
                case 4:

                    break;
                case 5:
                    //Sort ArrayList
                    removeCarnets();
                    break;
                case 0:
                    //Serialización
                    try {
                        serialize.guardar();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\nAdios!\n");
                    break;
                default:
                    select = 10;
                    break;
            }
        }while (select != 0);

    }

    public static ArrayList<CarnetCruzRoja> addCarnets() {
        ArrayList<CarnetCruzRoja> carnet = new ArrayList<>();
        boolean opcion = true;
        char salida;
        int i = 0;
        do {
            clearConsole();
            System.out.println("    ************************ Insercción de Carnets ************************");
            leer.nextLine(); //LIMPIO EL BUFFER PARA QUE NO SE SALTE CAMPOS
            carnet.add(new CarnetCruzRoja(prompt("el D.N.I:")));
            carnet.get(i).setNombre(prompt("el nombre:"));
            carnet.get(i).setApellidos(prompt("los apellidos:"));
            carnet.get(i).setProvincia(prompt("la provincia:"));
            carnet.get(i).setLocalidad(prompt("la localidad:"));
            carnet.get(i).setServicio(prompt("el servicio:"));

            do {
                try {
                    carnet.get(i).setFecha(prompt("la fecha (d/m/y):"));
                } catch (ParseException e) {
                    System.out.println("--> Error: No ha introducido la fecha en el formato correcto.");
                }
            } while (carnet.get(i).getFecha() == null);

            do {
                System.out.println("\n¿Desea introducir otro carnet? <y/n>");
                salida = leer.next().charAt(0);
                if (salida == 'n') opcion = false;
                else if (salida != 'y') System.out.println("\n--> Error: La opción intoducida no es válida");
            }while (salida != 'n' && salida != 'y');

            ++i;
        }while (opcion);
        return carnet;
    }

    private static String prompt(String campo){
        String resultado;
        do {
            System.out.println("\nIntroduce " + campo);
            resultado = leer.nextLine();
            if (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):")) System.out.println("--> Error: Debe introducir " + campo);
        } while (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):"));
        return resultado;
    }

    private static void printCarnets(ArrayList<CarnetCruzRoja> carnetList) {
        clearConsole();
        System.out.println("    ************************** Listado de Carnets *************************\n\n");
        for (CarnetCruzRoja aCarnetList : carnetList) {
            System.out.println("\t" + aCarnetList);
        }
        returnMenu();
    }

    private static void ordenaCarnets() {
        int select = 0;
        do {
            clearConsole();
            System.out.println("    ************************ Ordenación de Carnets ************************");

            if (select > 4) System.out.println("\n\n\t--> Error: Opción no válida");

            System.out.println("\n\n\tElige opción:\n\n" +
                    "\t1.- Ordenar carnets por apellido\n" +
                    "\t2.- Ordenar carnets por D.N.I\n" +
                    "\t3.- Ordenar carnets por fecha\n" +
                    "\t4.- Ordenar carnets por provincia\n" +
                    "\t0.- Salir\n");
            try {
                select = leer.nextInt();
            }catch (InputMismatchException e){
                System.out.println("\n\t--> Error: No ha introducido un carácter válido.");
                break;
            }
            switch (select){
                case 1:
                    //CompareTo Apellidos
                    Collections.sort(carnetList);
                    printCarnets(carnetList);
                    break;
                case 2:
                    //Compare Dni
                    Collections.sort(carnetList, new OrdenaCarnets("dni"));
                    printCarnets(carnetList);
                    break;
                case 3:
                    //Compare Date
                    Collections.sort(carnetList, new OrdenaCarnets("fecha"));
                    printCarnets(carnetList);
                    break;
                case 4:
                    //Compare Provincia
                    Collections.sort(carnetList, new OrdenaCarnets("provincia"));
                    printCarnets(carnetList);
                    break;
                case 5:
                    break;
                case 0:

                    break;
                default:
                    select = 10;
                    break;
            }
        } while (select != 0);
    }

    private static void removeCarnets() {
    }

    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void returnMenu(){
        System.out.println("\n\tPresione una tecla para volver al menú:");
        leer.nextLine();
        leer.nextLine();
    }
}
