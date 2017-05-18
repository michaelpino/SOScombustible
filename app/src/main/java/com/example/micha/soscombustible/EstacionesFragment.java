package com.example.micha.soscombustible;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EstacionesFragment extends Fragment {

    public EstacionesFragment() {
        // Required empty public constructor
    }

    private ListView vista;

    //Creo el arreglo de estaciones de servicio
    private Bencinera[] listaBencineras = new Bencinera[]{
            new Bencinera(1, "Estacion Simpson", "Av. siempre viva 742", 2),
            new Bencinera(2,"Estacion Flanders","Av. siempre viva 740",1),
            new Bencinera(3,"Estacion Moe","Av. desconocida 1234",3)};


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
            tv_nombre.setText(listaBencineras[posicion].getNombre());

            //Se rellena el textview de la direccion
            TextView textView2 = (TextView)item.findViewById(R.id.item_direccion);
            textView2.setText(listaBencineras[posicion].getDireccion());

            //Se rellena el imageview con el logo segun corresponda
            ImageView imageView1 = (ImageView)item.findViewById(R.id.item_logo);
            if (listaBencineras[posicion].getLogo()==1)
                imageView1.setImageResource(R.drawable.copec);
            else if (listaBencineras[posicion].getLogo()==2)
                imageView1.setImageResource(R.drawable.shell);
            else if (listaBencineras[posicion].getLogo()==3)
                imageView1.setImageResource(R.drawable.petrobras);
            else if (listaBencineras[posicion].getLogo()==4)
                imageView1.setImageResource(R.drawable.terpel);

            //Se rellena el imageview con el icono de "ver más"
            ImageView imageView2 = (ImageView)item.findViewById(R.id.item_vermas);
            imageView2.setImageResource(R.drawable.ic_storage);

            return(item);
        }
    }

    /*public void enContruccion(View vista) {
        Toast toast = Toast.makeText(, "Más detalles proximamente :)", Toast.LENGTH_SHORT);
        toast.show();
    }*/



    /*// Rename method, update argument and hook method into UI event
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
    }*/

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /*
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
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
