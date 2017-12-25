package com.gmail.cesarcanojmz.mislugares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EdicionLugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar);
    }

    public void lanzar_VistaLugar(View view) {
        Intent i = new Intent(getBaseContext(), VistaLugarActivity.class);
        i.putExtra("id", (long) 0);
        startActivity(i);
    }

    // < MENU >
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edicion_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.om_acercade:
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
    // </ MENU >
}
