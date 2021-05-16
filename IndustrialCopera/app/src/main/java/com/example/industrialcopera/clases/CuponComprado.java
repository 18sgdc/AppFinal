package com.example.industrialcopera.clases;

public class CuponComprado {
    String id, idCupon, idCliente;
    boolean concierto,porcentaje;
    double value, min;

    public CuponComprado( String idCupon, String idCliente, boolean concierto, boolean porcentaje, double value, double min) {
        this.id = "";
        this.idCupon = idCupon;
        this.idCliente = idCliente;
        this.concierto = concierto;
        this.porcentaje = porcentaje;
        this.value = value;
        this.min = min;
    }
    public CuponComprado() {
        this.id = "";
        this.idCupon = "";
        this.idCliente = "";
        this.concierto = false;
        this.porcentaje = false;
        this.value = 0;
        this.min = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(String idCupon) {
        this.idCupon = idCupon;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isConcierto() {
        return concierto;
    }

    public void setConcierto(boolean concierto) {
        this.concierto = concierto;
    }

    public boolean isPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        String texto;
        if(value==-1){
            texto="Selecciona un cupon";
        }else{
            if(porcentaje){
                texto=value+"% de descuento";
            }else{
                texto=value+"€ de descuento";
            }
        }
        return texto;
    }
}
