package com.ceste;

public class Main {

    public static void main(String[] args) {
	    CarnetCruzRoja carnet = new CarnetCruzRoja();
        CarnetCruzRoja carnet1 = new CarnetCruzRoja();
        carnet.setApellidos("pepe");
        carnet1.setApellidos("juan");
        System.out.println(carnet.getApellidos());
        System.out.println(carnet1.getApellidos());
    }

}
