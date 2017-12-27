package com.gmail.cesarcanojmz.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.gmail.cesarcanojmz.mislugares.Model.Lugar;
import com.gmail.cesarcanojmz.mislugares.Model.TipoLugar;

public class EdicionLugarActivity extends AppCompatActivity {

    private long id;
    private Lugar lugar;

    private EditText eT_nombreL;
    private EditText eT_direccionL;
    private EditText eT_telefonoL;
    private EditText eT_webL;
    private EditText eT_comentarioL;
    private Spinner sp_tipoL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_lugar);
        lugar = new Lugar();

                                                    // <!-- CASTEO
        eT_nombreL = findViewById(R.id.eT_nombre);
        eT_direccionL = findViewById(R.id.eT_direccion);
        eT_telefonoL = findViewById(R.id.eT_telefono);
        eT_webL = findViewById(R.id.eT_url);
        eT_comentarioL = findViewById(R.id.eT_comentario);
        sp_tipoL = findViewById(R.id.sp_tipo);
                                                    // CASTEO -->

                                                    // <!-- DATOS
        eT_nombreL.setText(lugar.getNombre());
        eT_direccionL.setText(lugar.getDireccion());
        eT_telefonoL.setText(Integer.toString(lugar.getTelefono()));
        eT_webL.setText(lugar.getUrl());
        eT_comentarioL.setText(lugar.getComentario());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipoL.setAdapter(adapter);
        sp_tipoL.setSelection(lugar.getTipoLugar().ordinal());

                                                    // DATOS -->

    }

                                                    /* <!-- MENU */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_edicion_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.om_cancelarEd:
                return true;
            case R.id.om_guardarEd:
                guardarEdicion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
                                                    /* MENU --> */

                                                    /* <!-- MENU ACTIONS */
    private void guardarEdicion() {
        lugar.setNombre(eT_nombreL.getText().toString());
        lugar.setTipoLugar(TipoLugar.values()[sp_tipoL.getSelectedItemPosition()]);
        lugar.setDireccion(eT_direccionL.getText().toString());
        lugar.setTelefono(Integer.parseInt(eT_telefonoL.getText().toString()));
        lugar.setUrl(eT_webL.getText().toString());
        lugar.setComentario(eT_comentarioL.getText().toString());
        finish();
    }
                                                    /* MENU ACTIONS -->*/
}
