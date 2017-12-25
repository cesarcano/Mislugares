package com.gmail.cesarcanojmz.mislugares.Model;

import com.gmail.cesarcanojmz.mislugares.R;

/**
 * Created by cesar on 25/12/17.
 */

public enum TipoLugar {
    OTROS("Otros", R.drawable.ic_lugar_tipo),
    RESTAURANTE("Restaurante", R.drawable.ic_restaurante),
    BAR("Bar", R.drawable.ic_bar),
    ESPECTACULO("Espectáculo", R.drawable.ic_evento),
    HOTEL("Hotel", R.drawable.ic_hotel),
    COMPRAS("Compras", R.drawable.ic_compras),
    EDUCACION("Educación", R.drawable.ic_educacion),
    DEPORTE("Deporte", R.drawable.ic_deporte),
    NATURALEZA("Naturaleza", R.drawable.ic_naturaleza),
    GASOLINERA("Gasolinera", R.drawable.ic_gasolinera);

    private final String texto;
    private final int recurso;

    TipoLugar(String texto, int recurso) {
        this.texto = texto;
        this.recurso = recurso;
    }

    public String getTexto() {
        return texto;
    }

    public int getRecurso() {
        return recurso;
    }

    public static  String[] getNombres() {
        String[] nombres = new String[TipoLugar.values().length];
        for (TipoLugar tipo : TipoLugar.values()) {
            nombres[tipo.ordinal()] = tipo.getTexto();
        }
        return nombres;
    }

}
