package com.example.micha.soscombustible;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //Creo el arreglo de estaciones de servicio
        Bencinera[] listaBencineras = new Bencinera[]{
                new Bencinera(1, "Estacion Simpson", "Av. siempre viva 742", 2),
                new Bencinera(2,"Estacion Flanders","Av. siempre viva 740",1),
                new Bencinera(3,"Estacion Moe","Av. desconocida 1234",3)};

        //Aqui se recibe el item seleccionado en el Fragment de origen
        Bundle info = this.getIntent().getExtras();
        int posicion = info.getInt("estacion_seleccionada");

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

    }
}
