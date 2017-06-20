package com.example.micha.soscombustible;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.micha.soscombustible.MainActivity.listaBencineras;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SosFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{
   /* // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;*/

    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    private GoogleApiClient apiClient;

    Location lastLocation;

    private Location miUbicacion;


   public SosFragment() {
        // Required empty public constructor
    }

    private ListView vista;

    //Creo el arreglo de estaciones de servicio
    //private List<Bencinera> listaSOS;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SosFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static SosFragment newInstance(String param1, String param2) {
        SosFragment fragment = new SosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        listaSOS = new ArrayList<>();
        listaSOS.add(new Bencinera("co120501",0,"Sociedad Comercial Ibañez Y Negron  Ltda.",-21.092258057248,-69.592823088169,
                "Panamericana Norte Km 1750, Ex S. V ",1401,1,"24 horas",0,764,0,564,0,0,true,true,true,true,false,false,false));*/

        apiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        //miUbicacion= ubicame();
        /*while (miUbicacion==null){
            miUbicacion= ubicame();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //miUbicacion= ubicame();
        /*while (miUbicacion!=null){
            miUbicacion= ubicame();
        }*/
        return inflater.inflate(R.layout.fragment_sos, container, false);

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {

            lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);

            miUbicacion = lastLocation;
            vista = (ListView)getView().findViewById(R.id.fragment_sos);

            vista.setAdapter(new SosFragment.AdaptadorSos(this));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    private void updateUI(Location loc) {
        String lat_text, long_text;
        if (loc != null) {
            lat_text = "Latitud: " + String.valueOf(loc.getLatitude());
            long_text = "Longitud: " + String.valueOf(loc.getLongitude());
        } else {
            lat_text = "Latitud: (desconocida)";
            long_text = "Longitud: (desconocida)";
        }

        //Toast.makeText(getActivity(), lat_text+"\n"+long_text, Toast.LENGTH_LONG).show();
    }

    public void showLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getActivity(), "No tiene permisos para usar el localizador", Toast.LENGTH_LONG).show();
        } else {

            lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);
        }
    }

    public Location ubicame() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getActivity(), "FALLO!!!", Toast.LENGTH_LONG).show();
            updateUI(lastLocation);
            return lastLocation;
        } else {

            lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);
            return lastLocation;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        apiClient.stopAutoManage(getActivity());
        apiClient.disconnect();
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        miUbicacion= ubicame();


        //showLocation();

        vista = (ListView)getView().findViewById(R.id.fragment_sos);

        vista.setAdapter(new SosFragment.AdaptadorSos(this));

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
    class AdaptadorSos extends ArrayAdapter<Bencinera> {
        //AppCompatActivity appCompatActivity;
        Activity context;

        AdaptadorSos(Fragment context) {
            super(context.getActivity(), R.layout.itemdesos, listaBencineras); //listaSOS
            this.context = context.getActivity();
        }

        public View getView(int posicion, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemdesos, null);

            Random rn = new Random();

            //Location miUbicacion= ubicame();
            float distancia;

            if (miUbicacion!= null){
                distancia = miUbicacion.distanceTo(listaBencineras.get(posicion).getUbicacion());
                distancia = distancia/1000;
                TextView tv_distancia = (TextView) item.findViewById(R.id.itemsos_distancia);
                tv_distancia.setText(" " + (int)distancia + " ");

            } else {
                //Se rellena el textview de la distancia
                TextView tv_distancia = (TextView) item.findViewById(R.id.itemsos_distancia);
                tv_distancia.setText( "Buscando señal..." );
            }




            //Se rellena el textview de la unidad de medida
            TextView tv_unidad = (TextView)item.findViewById(R.id.itemsos_unidadmedida);
            tv_unidad.setText("KMS");

            //Se rellena el textview de la direccion
            TextView textView2 = (TextView)item.findViewById(R.id.itemsos_direccion);
            textView2.setText(listaBencineras.get(posicion).getDireccion()); // <- listaSOS

            //Se rellena el imageview con el logo segun corresponda
            ImageView imageView1 = (ImageView)item.findViewById(R.id.itemsos_logo);
            if (listaBencineras.get(posicion).getBrand()==1)
                imageView1.setImageResource(R.drawable.ic_copec_horizontal);
            else if (listaBencineras.get(posicion).getBrand()==2)
                imageView1.setImageResource(R.drawable.ic_shell_horizontal);
            else if (listaBencineras.get(posicion).getBrand()==3)
                imageView1.setImageResource(R.drawable.ic_petrobras_horizontal);
            else if (listaBencineras.get(posicion).getBrand()==4)
                imageView1.setImageResource(R.drawable.ic_terpel_horizontal);
            else if (listaBencineras.get(posicion).getBrand()==5)
                imageView1.setImageResource(R.drawable.ic_lipigas_horizontal);
            else imageView1.setImageResource(R.drawable.ic_gasstation);

            //Se rellena los imageview de las caracteristicas de la estacion
            if (listaBencineras.get(posicion).isMp_cheque()) {
                imageView1 = (ImageView)item.findViewById(R.id.itemsos_carac1);
                imageView1.setImageResource(R.drawable.ic_cheque);
            }
            if (listaBencineras.get(posicion).isMp_tbk()) {
                imageView1 = (ImageView)item.findViewById(R.id.itemsos_carac2);
                imageView1.setImageResource(R.drawable.ic_redcompra);
            }
            if (listaBencineras.get(posicion).isMp_efectivo()) {
                imageView1 = (ImageView)item.findViewById(R.id.itemsos_carac3);
                imageView1.setImageResource(R.drawable.ic_efectivo);
            }
            if (listaBencineras.get(posicion).isSrv_mantencion()) {
                imageView1 = (ImageView)item.findViewById(R.id.itemsos_carac4);
                imageView1.setImageResource(R.drawable.ic_mantenciones);
            }
            if (listaBencineras.get(posicion).isSrv_farmacia()) {
                imageView1 = (ImageView)item.findViewById(R.id.itemsos_carac5);
                imageView1.setImageResource(R.drawable.ic_farmacia);
            }

            //Se rellena el textview del octanaje
            TextView tv_octanaje = (TextView)item.findViewById(R.id.itemsos_octanaje);
            tv_octanaje.setText("93 Octanos");

            //Se rellena el textview del precio
            TextView tv_precio = (TextView)item.findViewById(R.id.itemsos_precio);
            if(listaBencineras.get(posicion).getPrc_gas93() != 0) {
                tv_precio.setText("$" + listaBencineras.get(posicion).getPrc_gas93() + "/L");
            } else {
                tv_precio.setText(" Agotada ");
            }

            return(item);
        }
    }
    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
