package com.gmail.cesarcanojmz.mislugares;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.cesarcanojmz.mislugares.Model.Lugar;
import com.gmail.cesarcanojmz.mislugares.Model.Lugares;
import com.gmail.cesarcanojmz.mislugares.Model.TipoLugar;

import java.io.File;
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
    final static int RESULTADO_EDITAR = 1;
    final static int RESULTADO_GALERIA = 2;
    final static int RESULTADO_FOTO = 3;
    private Uri uriFoto;

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

        actualizarVista();

    }

    void actualizarVista() {
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

        updateFoto(iv_fotoL,lugar.getFoto());

    }

    public void galeria(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/");
        startActivityForResult(intent, RESULTADO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULTADO_EDITAR) {
            actualizarVista();
            findViewById(R.id.activity_vista_lugar).invalidate();
        } else if (requestCode == RESULTADO_GALERIA && resultCode == Activity.RESULT_OK) {
            lugar.setFoto(data.getDataString());
            updateFoto(iv_fotoL, lugar.getFoto());
        } else if(requestCode == RESULTADO_FOTO && resultCode == Activity.RESULT_OK
                    && lugar != null && uriFoto != null) {
            lugar.setFoto(uriFoto.toString());
            updateFoto(iv_fotoL, lugar.getFoto());
        }
    }

    private void updateFoto(ImageView imageView, String uri) {
        if (uri != null) {
            imageView.setImageURI(Uri.parse(uri));
        } else {
            imageView.setImageBitmap(null);
        }
    }

    public void tomarFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        uriFoto = Uri.fromFile(new File(
                Environment.getExternalStorageDirectory() + File.separator
                        + "img_" + (System.currentTimeMillis() / 1000) + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, RESULTADO_FOTO);
    }

    /* <!-- MENU  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_vista_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.om_compartir:
                compartirLugar(null);
                return true;
            case R.id.om_llegar:
                comoLlegar(null);
                return true;
            case R.id.om_editar:
                action_editarLugar();
                return true;
            case R.id.om_borrar:
                action_borrarLugar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
                                                /*  MENU --> */

                                                /* <!-- MENU ACTIONS  */
    public void action_borrarLugar () {
        new AlertDialog.Builder(this)
                .setTitle("Borrado de lugar")
                .setMessage("Está seguro que desea borrar este lugar?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Lugar Borrado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();

    }

    public void action_editarLugar () {
        new AlertDialog.Builder(this)
                .setTitle("Edición de lugar")
                .setMessage("Está seguro que desea editar este lugar?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getBaseContext(), EdicionLugarActivity.class);
                        startActivityForResult(i, 1234);
                        //startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();

    }

    public void compartirLugar(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, lugar.getNombre() + "-" + lugar.getUrl());
        startActivity(intent);
    }

    public void comoLlegar(View view){
        Uri uri;
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();

        if(lat != 0 || lon != 0 ) {
            uri = Uri.parse("geo:" + lat + "," + lon);
        } else {
            uri = Uri.parse("geo:0,0?q="+lugar.getDireccion());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void llamadaTelefonica(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:"+lugar.getTelefono())));
    }

    public void goToWebSite(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }
                                                /* MENU ACTIONS --> */
}
