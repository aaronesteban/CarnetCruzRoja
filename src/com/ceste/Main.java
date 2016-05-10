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
        //printCarnetFromArrayList(carnetList);

        menu();

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

    private static void menu() {
        int select = -1;

        System.out.println("*************************************************************");
        System.out.println("********** PRÁCTICA FINAL CARNETS BY AARON ESTEBAN **********");
        System.out.println("*************************************************************");

        //Mientras la opción elegida sea 0, preguntamos al usuario
        while(select != 0){
            //Try catch para evitar que el programa termine si hay un error
            try{
                System.out.println("\n\n\tElige opción:\n\n" +
                        "\t1.- Sumar\n" +
                        "\t2.- Restar\n" +
                        "\t3.- Multiplicar\n" +
                        "\t4.- Dividir\n" +
                        "\t0.- Salir\n");
                //Recoger una variable por consola
                select = leer.nextInt();

                //Ejemplo de switch case en Java
                switch(select){
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 0:
                        System.out.println("Adios!");
                        break;
                    default:
                        System.out.println("Número no reconocido");break;
                }

                System.out.println("\n"); //Mostrar un salto de línea en Java

            }catch(Exception e){
                System.out.println("Uoop! Error!");
            }
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
                else if (salida != 'y') System.out.println("\nError: La opción intoducida no es válida");
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

    public static void clearConsole()
    {
        for (int i=0; i<80; ++i) System.out.println("\n");
    }
}
