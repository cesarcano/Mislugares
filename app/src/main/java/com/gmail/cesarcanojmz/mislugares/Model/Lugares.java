package com.gmail.cesarcanojmz.mislugares.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesar on 25/12/17.
 */

public class Lugares {

    public static final String TAG = "Mis lugares" ;
    protected static List<Lugar> vectorLugares = ejemploLugares();
    public static Geopunto posicionActual = new Geopunto(0,0);


    public Lugares() {
        vectorLugares = ejemploLugares();
    }

    public static Lugar elemento (int id) {
        return vectorLugares.get(id);
    }

    static void addElemento (Lugar lugar) {
        vectorLugares.add(lugar);
    }

    static int nuevo () {
        Lugar lugar = new Lugar();
        vectorLugares.add(lugar);
        return vectorLugares.size()-1;
    }

    static void borrar (int id) {
        vectorLugares.remove(id);
    }

    public static int size () {
        return vectorLugares.size();
    }

    private static ArrayList<Lugar> ejemploLugares() {
        ArrayList<Lugar> lugares = new ArrayList<Lugar>();

        lugares.add( new Lugar("UNIDAD PROFESIONAL INTERDISCIPLINARIA DE INGENIERÍA Y CIENCIAS SOCIALES Y ADMINISTRATIVAS - IPN"
                , "Av. Té 950, Granjas México, 08400 Ciudad de México, CDMX",
                19.396111, -99.091944, TipoLugar.EDUCACION, 24234324, "http://www.upiicsa.ipn.mx",
                "El mejor lugar para formarse", 4) );
        return lugares;
    }
}
