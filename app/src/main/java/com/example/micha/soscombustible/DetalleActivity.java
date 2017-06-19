package com.example.micha.soscombustible;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.micha.soscombustible.MainActivity.listaBencineras;

public class DetalleActivity extends AppCompatActivity {

    //private List<Bencinera> listaBencineras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //Creo el arreglo de estaciones de servicio
        /*listaBencineras = new ArrayList<>();
        listaBencineras.add(new Bencinera("co120501",0,"Sociedad Comercial Ibañez Y Negron  Ltda.",-21.092258057248,-69.592823088169,
                "Panamericana Norte Km 1750, Ex S. V ",1401,1,"24 horas",0,764,0,564,0,0,true,true,true,true,false,false,false));*/

        //Aqui se recibe el item seleccionado en el Fragment de origen
        Bundle info = this.getIntent().getExtras();
        final int posicion = info.getInt("estacion_seleccionada");


        //Se rellena el imageview con el logo segun corresponda
        ImageView imageView1 = (ImageView)findViewById(R.id.detalle_logo);
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
        Random rn = new Random();
        if (listaBencineras.get(posicion).isMp_cheque()) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac1);
            imageView1.setImageResource(R.drawable.ic_cheque);
        }
        if (listaBencineras.get(posicion).isMp_tbk()) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac2);
            imageView1.setImageResource(R.drawable.ic_redcompra);
        }
        if (listaBencineras.get(posicion).isMp_efectivo()) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac3);
            imageView1.setImageResource(R.drawable.ic_efectivo);
        }
        if (listaBencineras.get(posicion).isSrv_mantencion()) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac4);
            imageView1.setImageResource(R.drawable.ic_mantenciones);
        }
        if (listaBencineras.get(posicion).isSrv_farmacia()) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac5);
            imageView1.setImageResource(R.drawable.ic_farmacia);
        }

        //Se rellena el textview del nombre
        TextView tv_nombre = (TextView)findViewById(R.id.detalle_nombre);
        tv_nombre.setText(listaBencineras.get(posicion).getRazon_social());

        //Se rellena el textview de la direccion
        TextView tv_direccion = (TextView)findViewById(R.id.detalle_direccion);
        tv_direccion.setText(listaBencineras.get(posicion).getDireccion());

        //Se rellena el textview de la region
        TextView tv_region = (TextView)findViewById(R.id.detalle_region);
        //tv_region.setText(listaBencineras[posicion].getDireccion());
        tv_region.setText("REGION");

        //Se rellena el textview de la comuna
        TextView tv_comuna = (TextView)findViewById(R.id.detalle_comuna);
        //tv_comuna.setText(listaBencineras[posicion].getDireccion());
        tv_comuna.setText("COMUNA");

        //Se rellena el textview del precio
        TextView tv_precio93 = (TextView)findViewById(R.id.detalle_precio1);
        tv_precio93.setText("$"+listaBencineras.get(posicion).getPrc_gas93()+"/L");

        TextView tv_precio95 = (TextView)findViewById(R.id.detalle_precio2);
        tv_precio95.setText("$"+listaBencineras.get(posicion).getPrc_gas95()+"/L");

        TextView tv_precio97 = (TextView)findViewById(R.id.detalle_precio3);
        tv_precio97.setText("$"+listaBencineras.get(posicion).getPrc_gas97()+"/L");


        Button verMapa = (Button) findViewById(R.id.detalle_vermapa);
        verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UbicacionActivity.class);
                intent.putExtra("latitud", listaBencineras.get(posicion).getUbicacion().getLatitude());
                intent.putExtra("longitud", listaBencineras.get(posicion).getUbicacion().getLongitude());
                startActivity(intent);
            }
        });

    }

}
