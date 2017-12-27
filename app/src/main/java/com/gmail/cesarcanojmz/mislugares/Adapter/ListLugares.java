package com.gmail.cesarcanojmz.mislugares.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gmail.cesarcanojmz.mislugares.Model.Lugar;
import com.gmail.cesarcanojmz.mislugares.Model.Lugares;
import com.gmail.cesarcanojmz.mislugares.R;

/**
 * Created by cesar on 26/12/17.
 */

public class ListLugares extends BaseAdapter {
    private LayoutInflater layoutInflater;
    TextView tV_Nombre;
    TextView tV_Direccion;
    RatingBar rB_Valoracion;
    ImageView iV_icono;

    public ListLugares(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Lugares.size();
    }

    @Override
    public Object getItem(int position) {
        return Lugares.elemento(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // Modelo mediante se manejará este list item
        Lugar lugar = Lugares.elemento(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_lista_lugares, null);
        }

        tV_Nombre = view.findViewById(R.id.tV_nombreList);
        tV_Direccion = view.findViewById(R.id.tV_direccionList);
        iV_icono = view.findViewById(R.id.iV_iconoList);
        rB_Valoracion = view.findViewById(R.id.rB_raitingList);

        int id = R.drawable.ic_lugar_tipo;

        switch (lugar.getTipoLugar()) {
            case BAR: id = R.drawable.ic_bar; break;
            case COMPRAS: id = R.drawable.ic_compras; break;
            case HOTEL: id = R.drawable.ic_hotel; break;
            case OTROS: id = R.drawable.ic_lugar_tipo; break;
            case DEPORTE: id = R.drawable.ic_deporte; break;
            case EDUCACION: id = R.drawable.ic_educacion; break;
            case GASOLINERA: id = R.drawable.ic_gasolinera; break;
            case NATURALEZA: id = R.drawable.ic_naturaleza; break;
            case ESPECTACULO: id = R.drawable.ic_evento; break;
            case RESTAURANTE: id = R.drawable.ic_restaurante; break;
        }

        // Iniciando los elementos del item
        iV_icono.setImageResource(id);
        tV_Nombre.setText(lugar.getNombre());
        tV_Direccion.setText(lugar.getDireccion());
        rB_Valoracion.setRating(lugar.getValoracion());
        return view;
    }
}
