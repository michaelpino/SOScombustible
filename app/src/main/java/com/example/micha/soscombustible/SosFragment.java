package com.example.micha.soscombustible;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SosFragment extends Fragment {
   /* // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;*/

    public SosFragment() {
        // Required empty public constructor
    }

    private ListView vista;

    //Creo el arreglo de estaciones de servicio
    private Bencinera[] listaSOS = new Bencinera[]{
            new Bencinera(1, "Estacion Simpson", "Av. siempre viva 742", 2),
            new Bencinera(2,"Estacion Flanders","Av. siempre viva 740",1),
            new Bencinera(3,"Estacion Moe","Av. desconocida 1234",3)};
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
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        vista = (ListView)getView().findViewById(R.id.fragment_sos);

        vista.setAdapter(new SosFragment.AdaptadorSos(this));
    }

    //En el constructor llega como parámetro la referencia del ActionBarActivity que contiene el ListView (definimos un atributo para almacenar dicha referencia), también pasamos al constructor de la clase padre mediante el comando super la referencia del ActionBarActivity y el archivo XML asociado a cada item que lo llamamos "itemdelista" y finalmente el ArrayList respectivo
    class AdaptadorSos extends ArrayAdapter<Bencinera> {
        //AppCompatActivity appCompatActivity;
        Activity context;

        AdaptadorSos(Fragment context) {
            super(context.getActivity(), R.layout.itemdesos, listaSOS);
            this.context = context.getActivity();
        }

        public View getView(int posicion, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemdesos, null);

            //Se rellena el textview de la distancia
            TextView tv_distancia = (TextView)item.findViewById(R.id.itemsos_distancia);
            tv_distancia.setText("50");

            //Se rellena el textview de la unidad de medida
            TextView tv_unidad = (TextView)item.findViewById(R.id.itemsos_unidadmedida);
            tv_unidad.setText("KMS");

            //Se rellena el textview de la direccion
            TextView textView2 = (TextView)item.findViewById(R.id.itemsos_direccion);
            textView2.setText(listaSOS[posicion].getDireccion());

            //Se rellena el imageview con el logo segun corresponda
            ImageView imageView1 = (ImageView)item.findViewById(R.id.itemsos_logo);
            if (listaSOS[posicion].getLogo()==1)
                imageView1.setImageResource(R.drawable.ic_copec_horizontal);
            else if (listaSOS[posicion].getLogo()==2)
                imageView1.setImageResource(R.drawable.ic_shell_horizontal);
            else if (listaSOS[posicion].getLogo()==3)
                imageView1.setImageResource(R.drawable.ic_petrobras_horizontal);
            else if (listaSOS[posicion].getLogo()==4)
                imageView1.setImageResource(R.drawable.ic_terpel_horizontal);
            else if (listaSOS[posicion].getLogo()==5)
                imageView1.setImageResource(R.drawable.ic_lipigas_horizontal);

            //Se rellena el textview del octanaje
            TextView tv_octanaje = (TextView)item.findViewById(R.id.itemsos_octanaje);
            tv_octanaje.setText("93 Octanos");

            //Se rellena el textview del octanaje
            TextView tv_precio = (TextView)item.findViewById(R.id.itemsos_precio);
            tv_precio.setText("$680/L");

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
