package com.ceste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    final static Scanner LEER = new Scanner(System.in);
    static String dni;
    static ArrayList<CarnetCruzRoja> carnetList = new ArrayList<>();
    final static String NOMBRE_FICHERO = "datos.ser";
    final static String DNI = "el D.N.I:";
    final static String NOMBRE = "el nombre:";
    final static String APELLIDOS = "los apellidos:";
    final static String PROVINCIA = "la provincia:";
    final static String LOCALIDAD = "la localidad:";
    final static String SERVICIO = "el servicio:";
    static CarnetsCruzRojaDb serialize = new CarnetsCruzRojaDb(NOMBRE_FICHERO);

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
                    "\t4.- Editar carnets\n" +
                    "\t5.- Eliminar carnets\n" +
                    "\t0.- Guardar y salir\n");
            try {
                select = LEER.nextInt();
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
                    printCarnets();
                    break;
                case 3:
                    ordenaCarnets();
                    break;
                case 4:
                    editCarnets();
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
            System.out.println("    ************************ Insertar carnets ************************");
            LEER.nextLine(); //LIMPIO EL BUFFER PARA QUE NO SE SALTE CAMPOS
            carnet.add(new CarnetCruzRoja(prompt(DNI)));
            carnet.get(i).setNombre(prompt(NOMBRE));
            carnet.get(i).setApellidos(prompt(APELLIDOS));
            carnet.get(i).setProvincia(prompt(PROVINCIA));
            carnet.get(i).setLocalidad(prompt(LOCALIDAD));
            carnet.get(i).setServicio(prompt(SERVICIO));

            do {
                try {
                    carnet.get(i).setFecha(prompt("la fecha (d/m/y):"));
                } catch (ParseException e) {
                    System.out.println("--> Error: No ha introducido la fecha en el formato correcto.");
                }
            } while (carnet.get(i).getFecha() == null);

            do {
                System.out.println("\n¿Desea introducir otro carnet? <y/n>.");
                salida = LEER.next().charAt(0);
                if (salida == 'n') opcion = false;
                else if (salida != 'y') System.out.println("\n--> Error: La opción intoducida no es válida.");
            }while (salida != 'n' && salida != 'y');

            ++i;
        }while (opcion);
        return carnet;
    }

    private static String prompt(String campo){
        String resultado;
        boolean dniDuplicado;

        if (campo.equals(DNI)) {
            do {
                System.out.println("\nIntroduce " + campo);
                resultado = LEER.nextLine();
                if (resultado.isEmpty()) System.out.println("--> Error: Debe introducir " + campo + ".");
                dniDuplicado = uniqueDNI(resultado);
                if (dniDuplicado) System.out.println("--> Error: El D.N.I introducido ya existe.");
            } while (dniDuplicado || resultado.isEmpty());
        }
        else {
            do {
                System.out.println("\nIntroduce " + campo);
                resultado = LEER.nextLine();
                if (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):"))
                    System.out.println("--> Error: Debe introducir " + campo + ".");
            } while (resultado.isEmpty() && !campo.equals("la fecha (d/m/y):"));
        }
        return resultado;
    }

    private static Boolean uniqueDNI(String resultado) {
        boolean dniDuplicado = false;
        for (CarnetCruzRoja aCarnetList : carnetList) {
            if (aCarnetList.getDni().equals(resultado)) {
                dniDuplicado = true;
                break;
            }
        }
        return dniDuplicado;
    }

    private static void printCarnets() {
        clearConsole();
        System.out.println("    ************************** Listar carnets *************************\n\n");
        if (carnetList.isEmpty()) System.out.println("\tNo hay ningún carnet para mostrar.");
        else {
            for (CarnetCruzRoja aCarnetList : carnetList) {
                System.out.println(" " + aCarnetList);
            }
        }
        returnMenu();
    }

    private static void ordenaCarnets() {
        int select = 0;
        do {
            clearConsole();
            System.out.println("    ************************ Ordenar carnets ************************");

            if (select > 4) System.out.println("\n\n\t--> Error: Opción no válida");

            System.out.println("\n\n\tElige opción:\n\n" +
                    "\t1.- Ordenar carnets por apellido\n" +
                    "\t2.- Ordenar carnets por D.N.I\n" +
                    "\t3.- Ordenar carnets por fecha\n" +
                    "\t4.- Ordenar carnets por provincia\n" +
                    "\t0.- Salir\n");
            try {
                select = LEER.nextInt();
            }catch (InputMismatchException e){
                System.out.println("\n\t--> Error: No ha introducido un carácter válido.");
                break;
            }
            switch (select){
                case 1:
                    //CompareTo Apellidos
                    Collections.sort(carnetList);
                    printCarnets();
                    break;
                case 2:
                    //Compare Dni
                    Collections.sort(carnetList, new OrdenaCarnets("dni"));
                    printCarnets();
                    break;
                case 3:
                    //Compare Date
                    Collections.sort(carnetList, new OrdenaCarnets("fecha"));
                    printCarnets();
                    break;
                case 4:
                    //Compare Provincia
                    Collections.sort(carnetList, new OrdenaCarnets("provincia"));
                    printCarnets();
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

    private static void editCarnets() {
        char opcion;
        CarnetCruzRoja editCarnet = new CarnetCruzRoja();
        clearConsole();
        System.out.println("    ************************** Editar carnets *************************\n\n");
        LEER.nextLine();

        editCarnet = searchCarnets(editCarnet);

        if (editCarnet.getDni().equals(dni)) {
            do {
                System.out.println("\n\t¿Está seguro que desea editar este carnet? <y/n>");
                opcion = LEER.next().charAt(0);
                switch (opcion) {
                    case 'y':
                        LEER.nextLine();
                        editCarnet.setDni("@@@");
                        editCarnet.setDni(prompt("el D.N.I:"));
                        editCarnet.setNombre(prompt("el nombre:"));
                        editCarnet.setApellidos(prompt("los apellidos:"));
                        editCarnet.setProvincia(prompt("la provincia:"));
                        editCarnet.setLocalidad(prompt("la localidad:"));
                        editCarnet.setServicio(prompt("el servicio:"));
                        do {
                            try {
                                editCarnet.setFecha(prompt("la fecha (d/m/y):"));
                            } catch (ParseException e) {
                                System.out.println("--> Error: No ha introducido la fecha en el formato correcto.");
                            }
                        } while (editCarnet.getFecha() == null);
                        System.out.println("\n\tEl carnet ha sido editado.\n");
                        System.out.println(" " + editCarnet);
                        returnMenu();
                        break;
                    case 'n':
                        break;
                    default:
                        System.out.println("\n\t--> Error: La opción intoducida no es válida");
                }
            } while (opcion != 'n' && opcion != 'y');
        }
    }

    private static void removeCarnets() {
        char opcion;
        CarnetCruzRoja removeCarnet = new CarnetCruzRoja();
        clearConsole();
        System.out.println("    ************************** Eliminar carnets *************************\n\n");
        LEER.nextLine();

        removeCarnet = searchCarnets(removeCarnet);

        if (removeCarnet.getDni().equals(dni)) {
            do {
                System.out.println("\n\t¿Está seguro que desea eliminar este carnet? <y/n>.");
                opcion = LEER.next().charAt(0);
                switch (opcion) {
                    case 'y':
                        carnetList.remove(removeCarnet);
                        System.out.println("\n\tEl carnet ha sido eliminado.");
                        returnMenu();
                        break;
                    case 'n':
                        break;
                    default:
                        System.out.println("\n\t--> Error: La opción intoducida no es válida.");
                }
            } while (opcion != 'n' && opcion != 'y');
        }
    }

    private static CarnetCruzRoja searchCarnets(CarnetCruzRoja Carnet) {
        dni="";
        do {
            System.out.println("\tInserta el D.N.I del carnet a buscar:\n");
            dni = LEER.nextLine();
            if (dni.isEmpty()) System.out.println("\t--> Error: Debe introducir un D.N.I para realizar la búsqueda.\n");
        }while (dni.isEmpty());

        for (CarnetCruzRoja aCarnetList : carnetList) {
            if (aCarnetList.getDni().equals(dni)) {
                Carnet = aCarnetList;
                System.out.println("\n\tCarnet encontrado.");
                System.out.println("\n " + aCarnetList);
                return Carnet;
            }
        }
        System.out.println("\n\tCarnet no encontrado.");
        returnMenu();

        return Carnet;
    }

    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void returnMenu(){
        System.out.println("\n\tPresione una tecla para volver al menú:");
        LEER.nextLine();
        LEER.nextLine();
    }
}
