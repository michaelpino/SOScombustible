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

import java.util.Random;

public class DetalleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //Creo el arreglo de estaciones de servicio
        final Bencinera[] listaBencineras = new Bencinera[]{
                new Bencinera(1, "UNAB Republica", "Republica 239", 2, -33.451255, -70.667884),
                new Bencinera(2, "UNAB Antonio Varas","Antonio varas 810",1, -33.434667, -70.614825),
                new Bencinera(3, "UNAB Casona","Fernández Concha 700",3, -33.373960, -70.504978)};

        //Aqui se recibe el item seleccionado en el Fragment de origen
        Bundle info = this.getIntent().getExtras();
        final int posicion = info.getInt("estacion_seleccionada");


        //Se rellena el imageview con el logo segun corresponda
        ImageView imageView1 = (ImageView)findViewById(R.id.detalle_logo);
        if (listaBencineras[posicion].getLogo()==1)
            imageView1.setImageResource(R.drawable.ic_copec_horizontal);
        else if (listaBencineras[posicion].getLogo()==2)
            imageView1.setImageResource(R.drawable.ic_shell_horizontal);
        else if (listaBencineras[posicion].getLogo()==3)
            imageView1.setImageResource(R.drawable.ic_petrobras_horizontal);
        else if (listaBencineras[posicion].getLogo()==4)
            imageView1.setImageResource(R.drawable.ic_terpel_horizontal);
        else if (listaBencineras[posicion].getLogo()==5)
            imageView1.setImageResource(R.drawable.ic_lipigas_horizontal);



        //Se rellena los imageview de las caracteristicas de la estacion
        Random rn = new Random();
        if (rn.nextDouble()>0.5) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac1);
            imageView1.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (rn.nextDouble()>0.5) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac2);
            imageView1.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (rn.nextDouble()>0.5) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac3);
            imageView1.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (rn.nextDouble()>0.5) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac4);
            imageView1.setImageResource(R.mipmap.ic_launcher_round);
        }
        if (rn.nextDouble()>0.5) {
            imageView1 = (ImageView)findViewById(R.id.detalle_carac5);
            imageView1.setImageResource(R.mipmap.ic_launcher_round);
        }

        //Se rellena el textview del nombre
        TextView tv_nombre = (TextView)findViewById(R.id.detalle_nombre);
        tv_nombre.setText(listaBencineras[posicion].getNombre());

        //Se rellena el textview de la direccion
        TextView tv_direccion = (TextView)findViewById(R.id.detalle_direccion);
        tv_direccion.setText(listaBencineras[posicion].getDireccion());

        //Se rellena el textview de la region
        TextView tv_region = (TextView)findViewById(R.id.detalle_region);
        tv_region.setText(listaBencineras[posicion].getDireccion());

        //Se rellena el textview de la comuna
        TextView tv_comuna = (TextView)findViewById(R.id.detalle_comuna);
        tv_comuna.setText(listaBencineras[posicion].getDireccion());

        //Se rellena el textview del precio
        TextView tv_precio93 = (TextView)findViewById(R.id.detalle_octanaje1);
        tv_precio93.setText("$"+rn.nextInt(700)+"/L");

        TextView tv_precio95 = (TextView)findViewById(R.id.detalle_octanaje2);
        tv_precio95.setText("$"+rn.nextInt(700)+"/L");

        TextView tv_precio97 = (TextView)findViewById(R.id.detalle_octanaje3);
        tv_precio97.setText("$"+rn.nextInt(700)+"/L");


        Button verMapa = (Button) findViewById(R.id.detalle_vermapa);
        verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UbicacionActivity.class);
                intent.putExtra("latitud", listaBencineras[posicion].getLatitud());
                intent.putExtra("longitud", listaBencineras[posicion].getLongitud());
                startActivity(intent);
            }
        });

    }

}
