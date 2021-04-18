package com.example.industrialcopera.clases;

public class Producto {
    private String id,nombre, descripcion, urlFoto;
    private double precio;
    private int stock;

    public Producto(String nombre, String descripcion, double precio, int stock) {
        this.id = "";
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlFoto = "";
        this.precio = precio;
        this.stock = stock;
    }

    public Producto() {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
        this.urlFoto = "";
        this.precio = 0;
        this.stock = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

