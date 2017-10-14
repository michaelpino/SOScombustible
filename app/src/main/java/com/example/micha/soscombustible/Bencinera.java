package com.example.micha.soscombustible;

import android.location.Location;

/**
 * Created by micha on 15-05-2017.
 */

public class Bencinera {
    private String id;
    private int brand;
    private String razon_social;
    private Location ubicacion;
    private String direccion;
    private int region;
    private int comuna;
    private String horario;
    private int prc_gas93;
    private int prc_gas95;
    private int prc_gas97;
    private int prc_diesel;
    private int prc_glp;
    private int prc_gnc;
    private boolean mp_efectivo;
    private boolean mp_cheque;
    private boolean mp_onus;
    private boolean mp_tbk;
    private boolean srv_tienda;
    private boolean srv_farmacia;
    private boolean srv_mantencion;

    public Bencinera(String id, int brand, String razon_social, double latitud, double longitud, String direccion, int region, int comuna, String horario,
                     int prc_gas93, int prc_gas95, int prc_gas97, int prc_diesel, int prc_glp, int prc_gnc, boolean mp_efectivo, boolean mp_cheque,
                     boolean mp_onus, boolean mp_tbk, boolean srv_tienda, boolean srv_farmacia, boolean srv_mantencion) {
        this.id = id;
        this.brand = brand;
        this.razon_social = razon_social;
        this.ubicacion = new Location("");
        this.ubicacion.setLatitude(latitud);
        this.ubicacion.setLongitude(longitud);
        this.direccion = direccion;
        this.region = region;
        this.comuna = comuna;
        this.horario = horario;
        this.prc_gas93 = prc_gas93;
        this.prc_gas95 = prc_gas95;
        this.prc_gas97 = prc_gas97;
        this.prc_diesel = prc_diesel;
        this.prc_glp = prc_glp;
        this.prc_gnc = prc_gnc;
        this.mp_efectivo = mp_efectivo;
        this.mp_cheque = mp_cheque;
        this.mp_onus = mp_onus;
        this.mp_tbk = mp_tbk;
        this.srv_tienda = srv_tienda;
        this.srv_farmacia = srv_farmacia;
        this.srv_mantencion = srv_mantencion;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getBrand() { return brand; }
    public void setBrand(int brand) { this.brand = brand; }

    public String getRazon_social() { return razon_social; }
    public void setRazon_social(String razon_social) { this.razon_social = razon_social;}

    public Location getUbicacion() { return ubicacion; }
    public void setUbicacion(double latitud, double longitud) {
        this.ubicacion.setLatitude(latitud);
        this.ubicacion.setLongitude(longitud);
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getRegion() { return region; }
    public void setRegion(int region) { this.region = region; }

    public int getComuna() { return comuna; }
    public void setComuna(int comuna) { this.comuna = comuna; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getPrc_gas93() { return prc_gas93; }

    public void setPrc_gas93(int prc_gas93) { this.prc_gas93 = prc_gas93; }
    public int getPrc_gas95() { return prc_gas95; }

    public void setPrc_gas95(int prc_gas95) { this.prc_gas95 = prc_gas95; }
    public int getPrc_gas97() { return prc_gas97; }

    public void setPrc_gas97(int prc_gas97) { this.prc_gas97 = prc_gas97; }
    public int getPrc_diesel() { return prc_diesel; }

    public void setPrc_diesel(int prc_diesel) { this.prc_diesel = prc_diesel; }

    public int getPrc_glp() { return prc_glp; }
    public void setPrc_glp(int prc_glp) { this.prc_glp = prc_glp; }

    public int getPrc_gnc() { return prc_gnc; }
    public void setPrc_gnc(int prc_gnc) { this.prc_gnc = prc_gnc; }

    public boolean isMp_efectivo() { return mp_efectivo; }
    public void setMp_efectivo(boolean mp_efectivo) { this.mp_efectivo = mp_efectivo; }

    public boolean isMp_cheque() { return mp_cheque; }
    public void setMp_cheque(boolean mp_cheque) { this.mp_cheque = mp_cheque; }

    public boolean isMp_onus() { return mp_onus; }
    public void setMp_onus(boolean mp_onus) { this.mp_onus = mp_onus; }

    public boolean isMp_tbk() { return mp_tbk; }
    public void setMp_tbk(boolean mp_tbk) { this.mp_tbk = mp_tbk; }

    public boolean isSrv_tienda() { return srv_tienda; }
    public void setSrv_tienda(boolean srv_tienda) { this.srv_tienda = srv_tienda; }

    public boolean isSrv_farmacia() { return srv_farmacia; }
    public void setSrv_farmacia(boolean srv_farmacia) { this.srv_farmacia = srv_farmacia; }

    public boolean isSrv_mantencion() { return srv_mantencion; }
    public void setSrv_mantencion(boolean srv_mantencion) { this.srv_mantencion = srv_mantencion; }
}
