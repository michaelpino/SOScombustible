package com.example.micha.soscombustible;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueBest, latitudeValueBest;
    TextView longitudeValueGPS, latitudeValueGPS;
    TextView longitudeValueNetwork, latitudeValueNetwork;






    public static final String TAGLOG = "firebase-db";




    public static List<Bencinera> listaBencineras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // posicion gps Internet
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        // TODO Auto-generated method stub
        setContentView(R.layout.activity_splash);

        listaBencineras = new ArrayList<>();

        Query dbBencineras = FirebaseDatabase.getInstance().getReference().child("bencineras").orderByChild("id_comuna");

        ValueEventListener bencinerasListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
                final long[] pendingLoadCount = { dataSnapshot.getChildrenCount() };
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    listaBencineras.add(new Bencinera(
                            (String) child.getKey(),
                            child.child("brand").getValue(Integer.class),
                            (String) child.child("razon_social").getValue(),
                            (double) child.child("latitud").getValue(),
                            (double) child.child("longitud").getValue(),
                            (String) child.child("direccion").getValue(),
                            child.child("id_region").getValue(Integer.class),
                            child.child("id_comuna").getValue(Integer.class),
                            (String) child.child("horario").getValue(),
                            child.child("prc_gas93").getValue(Integer.class),
                            child.child("prc_gas95").getValue(Integer.class),
                            child.child("prc_gas97").getValue(Integer.class),
                            child.child("prc_diesel").getValue(Integer.class),
                            child.child("prc_glp").getValue(Integer.class),
                            child.child("prc_gnc").getValue(Integer.class),
                            Boolean.parseBoolean((String) child.child("mp_efectivo").getValue()),
                            Boolean.parseBoolean((String) child.child("mp_cheque").getValue()),
                            Boolean.parseBoolean((String) child.child("mp_onus").getValue()),
                            Boolean.parseBoolean((String) child.child("mp_tbk").getValue()),
                            Boolean.parseBoolean((String) child.child("srv_tienda").getValue()),
                            Boolean.parseBoolean((String) child.child("srv_tienda").getValue()),
                            Boolean.parseBoolean((String) child.child("srv_mantencion").getValue())));

                    pendingLoadCount[0] = pendingLoadCount[0] - 1;
                    if (pendingLoadCount[0] == 0) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        Bundle bundle = new Bundle();
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        dbBencineras.addValueEventListener(bencinerasListener);
    }
    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueGPS.setText(longitudeGPS + "");
                    latitudeValueGPS.setText(latitudeGPS + "");
                    Toast.makeText(SplashActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };
}

