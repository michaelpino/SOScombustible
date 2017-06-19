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
import com.google.firebase.database.Query;
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
}
