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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.gmail.cesarcanojmz.mislugares.Adapter.ListLugares;

public class MainActivity extends AppCompatActivity {

    public ListLugares adapterlistLugares; // Adaptador de la lista
    ListView listLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargando lista
        loadListLugares();
    }

    private void loadListLugares() {
        adapterlistLugares = new ListLugares(this);
        listLugares = findViewById(R.id.list_Lugares);
        listLugares.setAdapter(adapterlistLugares);
    }
                                                    /* <!-- ACTIONS MENU ---*/
    public void lanzar_VistaLugar(View view) {
        final EditText et_entrada = new EditText(this);
        et_entrada.setText("0");

        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("Indica su Id:")
                .setView(et_entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long id = Long.parseLong(et_entrada.getText().toString());
                        Intent i = new Intent(getBaseContext(), VistaLugarActivity.class);
                        i.putExtra("id", (long) id);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();

    }

    private void lanzar_VistaAcercaDe() {
        Intent i = new Intent(getBaseContext(), AcercadeActivity.class);
        startActivity(i);
    }
                                                    /* --- ACTIONS MENU --> */

                                                /* <!-- MENU  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.om_acercade:
                lanzar_VistaAcercaDe();
                return true;
            case R.id.om_configuracion:
                return true;
            case R.id.om_buscar:
                lanzar_VistaLugar(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
                                                /* MENU --> */
}
