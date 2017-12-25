package com.gmail.cesarcanojmz.mislugares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gmail.cesarcanojmz.mislugares.Model.Lugar;
import com.gmail.cesarcanojmz.mislugares.Model.Lugares;
import com.gmail.cesarcanojmz.mislugares.Model.TipoLugar;

import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {

    private long id;
    private Lugar lugar;
    TextView tv_nombreL;
    ImageView iv_tipoL;
    TextView tv_tipoL;
    TextView tv_direccionL;
    TextView tv_telefonoL;
    TextView tv_webL;
    TextView tv_comentarioL;
    TextView tv_fechaL;
    TextView tv_horaL;
    RatingBar rb_valoracionL;
    ImageView iv_fotoL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_lugar);
        // < Extras >
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        // </ Extras >
        lugar = Lugares.elemento((int) id);

        // < CASTEO >
        tv_nombreL = findViewById(R.id.tv_nombre_lugar);
        tv_tipoL = findViewById(R.id.tv_tipo_lugar);
        iv_tipoL = findViewById(R.id.ic_tipo_lugar);
        tv_direccionL = findViewById(R.id.tv_direccion_lugar);
        tv_telefonoL = findViewById(R.id.tv_telefono_lugar);
        tv_webL = findViewById(R.id.tv_url_lugar);
        tv_comentarioL = findViewById(R.id.tv_comentario_lugar);
        tv_fechaL = findViewById(R.id.tv_fecha_lugar);
        tv_horaL = findViewById(R.id.tv_hora_lugar);
        rb_valoracionL = findViewById(R.id.rb_valoracion_lugar);
        iv_fotoL = findViewById(R.id.img_foto_lugar);
        // </ CASTEO >

        tv_nombreL.setText(lugar.getNombre());
        tv_tipoL.setText(lugar.getTipoLugar().getTexto());
        iv_tipoL.setImageResource(lugar.getTipoLugar().getRecurso());
        tv_direccionL.setText(lugar.getDireccion());
        tv_telefonoL.setText(Integer.toString(lugar.getTelefono()));
        tv_webL.setText(lugar.getUrl());
        tv_comentarioL.setText(lugar.getComentario());
        tv_fechaL.setText(DateFormat.getDateInstance().format(new Date(lugar.getFecha())));
        tv_horaL.setText(DateFormat.getTimeInstance().format(new Date(lugar.getFecha())));
        rb_valoracionL.setRating(lugar.getValoracion());

        rb_valoracionL.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                lugar.setValoracion(rating);
            }
        });

    }
}
