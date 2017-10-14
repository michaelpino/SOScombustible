package com.example.micha.soscombustible;

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
//import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.micha.soscombustible.MainActivity.TAGLOG;
import static com.example.micha.soscombustible.MainActivity.listaBencineras;

public class EstacionesFragment extends Fragment {

    public EstacionesFragment() {
        // Required empty public constructor
    }

    private ListView vista;

    //Creo el arreglo de estaciones de servicio
    //private List<Bencinera> listaBencineras;
    /*
     []  = new Bencinera[]{
            new Bencinera(1, "UNAB Republica", "Republica 239", 2, -33.451255, -70.667884),
            new Bencinera(2, "UNAB Antonio Varas","Antonio varas 810",1, -33.434667, -70.614825),
            new Bencinera(3, "UNAB Casona","Fernández Concha 700",3, -33.373960, -70.504978)};*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*listaBencineras = new ArrayList<>();

        DatabaseReference dbBencineras = FirebaseDatabase.getInstance().getReference().child("bencineras");

        ValueEventListener bencinerasListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAGLOG, "onDataChange: {" + dataSnapshot.getKey() + ": " + dataSnapshot.getValue() + "}");
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
                    /*Iterator<String> regionesIterator = regiones.iterator();
                    Toast.makeText(getApplicationContext(), regionesIterator.next(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        dbBencineras.addValueEventListener(bencinerasListener);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estaciones, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        vista = (ListView)getView().findViewById(R.id.fragment_estaciones);

        vista.setAdapter(new AdaptadorBencinera(this));

        //Aqui se configura que al hacer click en un item se abra un activity de detalle de la estacion
        vista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetalleActivity.class);
                intent.putExtra("estacion_seleccionada", position);
                startActivity(intent);
            }
        });
    }

    //En el constructor llega como parámetro la referencia del ActionBarActivity que contiene el ListView (definimos un atributo para almacenar dicha referencia), también pasamos al constructor de la clase padre mediante el comando super la referencia del ActionBarActivity y el archivo XML asociado a cada item que lo llamamos "itemdelista" y finalmente el ArrayList respectivo
    class AdaptadorBencinera extends ArrayAdapter<Bencinera> {
        //AppCompatActivity appCompatActivity;
        Activity context;

        AdaptadorBencinera(Fragment context) {
            super(context.getActivity(), R.layout.itemdelista, listaBencineras);
            this.context = context.getActivity();
        }

        public View getView(int posicion, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemdelista, null);

            //Se rellena el textview del nombre
            TextView tv_nombre = (TextView)item.findViewById(R.id.item_nombre);
            tv_nombre.setText(listaBencineras.get(posicion).getRazon_social());

            //Se rellena el textview de la direccion
            TextView textView2 = (TextView)item.findViewById(R.id.item_direccion);
            textView2.setText(listaBencineras.get(posicion).getDireccion());

            //Se rellena el imageview con el logo segun corresponda
            ImageView imageView1 = (ImageView)item.findViewById(R.id.item_logo);
            if (listaBencineras.get(posicion).getBrand()==1){
                imageView1.setImageResource(R.drawable.ic_copec_horizontal);}
            else if (listaBencineras.get(posicion).getBrand()==2){
                imageView1.setImageResource(R.drawable.ic_shell_horizontal);}
            else if (listaBencineras.get(posicion).getBrand()==3){
                imageView1.setImageResource(R.drawable.ic_petrobras_horizontal);}
            else if (listaBencineras.get(posicion).getBrand()==4){
                imageView1.setImageResource(R.drawable.ic_terpel_horizontal);}
            else if (listaBencineras.get(posicion).getBrand()==5){
                imageView1.setImageResource(R.drawable.ic_lipigas_horizontal);}
            else {imageView1.setImageResource(R.drawable.ic_gasstation);}

            //Se rellena el imageview con el icono de "ver más"
            ImageView imageView2 = (ImageView)item.findViewById(R.id.item_vermas);
            imageView2.setImageResource(R.drawable.ic_storage);

            return(item);
        }
    }


}
