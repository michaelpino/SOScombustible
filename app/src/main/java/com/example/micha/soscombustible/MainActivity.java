package com.example.micha.soscombustible;

//import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
//import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAGLOG = "firebase-db";

    public static List<String> regiones;
    public static List<String> marcas;
    public static List<Bencinera> listaBencineras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        regiones = new ArrayList<>();
        marcas = new ArrayList<>();
        listaBencineras = new ArrayList<>();

        DatabaseReference dbRegiones = FirebaseDatabase.getInstance().getReference().child("regiones");
        DatabaseReference dbMarcas = FirebaseDatabase.getInstance().getReference().child("marcas");
        Query dbBencineras = FirebaseDatabase.getInstance().getReference().child("bencineras").orderByChild("id_comuna");

        ValueEventListener regionesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    regiones.add(postSnapshot.getValue().toString());
                    /*Iterator<String> regionesIterator = regiones.iterator();
                    Toast.makeText(getApplicationContext(), regionesIterator.next(), Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        ValueEventListener marcasListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    marcas.add(postSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        ValueEventListener bencinerasListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
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

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        dbBencineras.addValueEventListener(bencinerasListener);

        dbRegiones.addValueEventListener(regionesListener);

        dbMarcas.addValueEventListener(marcasListener);

        //Toast.makeText(this,regiones.toString(),Toast.LENGTH_SHORT).show();

        //Aqui inicia el fragment de SOS (Pantalla iicial)
        Fragment fragment = null;
        fragment = new SosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        int fragmento_seleccionado = 0;

        if (id == R.id.menu_sos) {
            fragment = new SosFragment();
            fragmento_seleccionado = 1;
        } else if (id == R.id.menu_estaciones) {
            fragment = new EstacionesFragment();
            fragmento_seleccionado = 2;
        } else if (id == R.id.menu_ayuda) {
            fragment = new AyudaFragment();
            fragmento_seleccionado = 3;
        } else if (id == R.id.menu_configuracion) {
            fragment = new ConfiguracionFragment();
            fragmento_seleccionado = 4;
        }

        if(fragmento_seleccionado!=0){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
