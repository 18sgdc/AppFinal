package com.example.industrialcopera.clases;

public class Cupon {
    private String id;
    private int precio, stock; //int por que son puntos
    private double valor, min;
    private boolean concierto, porcentaje;

    public Cupon( boolean concierto, int precio, int stock,boolean porcentaje, double valor, double min) {
        this.id = "";
        this.precio = precio;
        this.stock = stock;
        this.valor = valor;
        this.min = min;
        this.concierto = concierto;
        this.porcentaje = porcentaje;
    }

    public Cupon() {
        this.id = "";
        this.precio = 0;
        this.stock = 0;
        this.valor = 0;
        this.min = 0;
        this.concierto = true;
        this.porcentaje = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
