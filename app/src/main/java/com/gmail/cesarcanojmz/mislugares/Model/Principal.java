package com.gmail.cesarcanojmz.mislugares.Model;

/**
 * Created by cesar on 24/12/17.
 */

public class Principal {
    public static void main (String args []) {
        Lugar lugar = new Lugar("UPIICSA", "Av. Té 950, Granjas México, 08400 Ciudad de México, CDMX",
                19.396111, -99.091944, 24234324, "http://www.upiicsa.ipn.mx",
                "El mejor lugar para formarse", 4);
        System.out.printf("Lugar: " + lugar.toString());
    }
}
