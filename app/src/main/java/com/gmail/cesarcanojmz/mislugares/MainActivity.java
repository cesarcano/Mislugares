package com.gmail.cesarcanojmz.mislugares;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.gmail.cesarcanojmz.mislugares.Adapter.ListLugares;
import com.gmail.cesarcanojmz.mislugares.Model.Lugares;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public ListLugares adapterlistLugares; // Adaptador de la lista

    private ListView listLugares;
    private LocationManager locationManager;
    private Location bestLocation;
    public static final long DOS_MINUTOS = 2 * 60 * 1000;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargando lista
        loadListLugares();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                // ask permissions here using below code
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE);
            }
            updateBestLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            updateBestLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        }

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

    private void updateBestLocation(Location location) {
        if (location != null && (bestLocation == null)
                || location.getAccuracy() < 2 * bestLocation.getAccuracy()
                || location.getTime() - bestLocation.getTime() < DOS_MINUTOS) {
            Log.d(Lugares.TAG, "Mejor locaclizacion");
            bestLocation = location;
            Lugares.posicionActual.setLatitud(location.getLatitude());
            Lugares.posicionActual.setLongitud(location.getLongitude());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activarProveedores();
    }

    private void activarProveedores() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
                // ask permissions here using below code
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    20 * 1000, 5, (LocationListener) this);
        }
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    20*1000, 10, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates( this);
    }

    @Override
    public void onLocationChanged(Location location) {
        updateBestLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(Lugares.TAG, "Cambia estado " + provider);
        activarProveedores();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(Lugares.TAG, "Se deshabilita " + provider);
        activarProveedores();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(Lugares.TAG, "Se habilita " + provider);
        activarProveedores();
    }

}
