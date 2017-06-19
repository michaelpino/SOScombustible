package com.example.micha.soscombustible;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static final String TAGLOG = "firebase-db";

    public static List<Bencinera> listaBencineras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //final Intent intent = new Intent(this, MainActivity.class);

        listaBencineras = new ArrayList<>();

        DatabaseReference dbBencineras = FirebaseDatabase.getInstance().getReference().child("bencineras");

        ValueEventListener bencinerasListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
                final long[] pendingLoadCount = { dataSnapshot.getChildrenCount() };
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    String id = (String) child.getKey();

                    String razon_social = (String) child.child("razon_social").getValue();
                    int brand = child.child("brand").getValue(Integer.class);
                    double latitud = (double) child.child("latitud").getValue();
                    double longitud = (double) child.child("longitud").getValue();
                    String direccion = (String) child.child("direccion").getValue();
                    int region = child.child("id_region").getValue(Integer.class);
                    int comuna = child.child("id_comuna").getValue(Integer.class);
                    String horario = (String) child.child("horario").getValue();
                    int prc_gas93 = child.child("prc_gas93").getValue(Integer.class);
                    int prc_gas95 = child.child("prc_gas95").getValue(Integer.class);
                    int prc_gas97 = child.child("prc_gas97").getValue(Integer.class);
                    int prc_diesel = child.child("brand").getValue(Integer.class);
                    int prc_glp = child.child("prc_glp").getValue(Integer.class);
                    int prc_gnc = child.child("prc_gnc").getValue(Integer.class);
                    boolean mp_efectivo = Boolean.parseBoolean((String) child.child("mp_efectivo").getValue());
                    boolean mp_cheque = Boolean.parseBoolean((String) child.child("mp_cheque").getValue());
                    boolean mp_onus = Boolean.parseBoolean((String) child.child("mp_onus").getValue());
                    boolean mp_tbk = Boolean.parseBoolean((String) child.child("mp_tbk").getValue());
                    boolean srv_tienda = Boolean.parseBoolean((String) child.child("razon_social").getValue());
                    boolean srv_farmacia = Boolean.parseBoolean((String) child.child("srv_tienda").getValue());
                    boolean srv_mantencion = Boolean.parseBoolean((String) child.child("srv_mantencion").getValue());

                    listaBencineras.add(new Bencinera(id,brand,razon_social,latitud,longitud,direccion,region,comuna,horario,prc_gas93,prc_gas95,
                            prc_gas97,prc_diesel,prc_glp,prc_gnc,mp_efectivo,mp_cheque,mp_onus,mp_tbk,srv_tienda,srv_farmacia,srv_mantencion));

                    pendingLoadCount[0] = pendingLoadCount[0] - 1;
                    if (pendingLoadCount[0] == 0) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        dbBencineras.addValueEventListener(bencinerasListener);
/*
        Thread logoTimer = new Thread(){
            public void run(){
                try{
                    int logoTimer = 0;
                    while (logoTimer<2000){
                        sleep(100);
                        logoTimer=logoTimer+100;
                    }
                    startActivity(intent);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    finish();
                }
            }
        };
        logoTimer.start();*/
    }
}
