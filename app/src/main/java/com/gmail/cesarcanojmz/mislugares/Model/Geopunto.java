package com.gmail.cesarcanojmz.mislugares.Model;

/**
 * Created by cesar on 24/12/17.
 */

public class Geopunto {
    private double latitud;
    private double longitud;


    public Geopunto(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String toString() {
        return new String("longitud: " + longitud + "Latitud: " + latitud);
    }

    public double distancia(Geopunto punto) {
        final double RADIO_TIERRA = 6371000; // distancia en metros
        double dLat = Math.toRadians(latitud - punto.latitud);
        double dLong = Math.toRadians(longitud - punto.longitud);
        double lat1 = Math.toRadians(punto.latitud);
        double lat2 = Math.toRadians(latitud);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.sin(dLong / 2) * Math.sin(dLong / 2) *
                   Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * RADIO_TIERRA;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
