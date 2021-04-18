package com.example.industrialcopera.clases;

public class Cupon {
    private String id,caducidad;
    private int precio; //int por que son puntos
    private double valor, min;
    private boolean concierto;

    public Cupon(String caducidad, int precio, double valor, double min, boolean concierto) {
        this.id = "";
        this.caducidad = caducidad;
        this.precio = precio;
        this.valor = valor;
        this.min = min;
        this.concierto = concierto;
    }

    public Cupon() {
        this.id = "";
        this.caducidad = "";
        this.precio = 0;
        this.valor = 0;
        this.min = 0;
        this.concierto = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public boolean isConcierto() {
        return concierto;
    }

    public void setConcierto(boolean concierto) {
        this.concierto = concierto;
    }
}
