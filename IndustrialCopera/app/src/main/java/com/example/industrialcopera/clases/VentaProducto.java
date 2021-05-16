package com.example.industrialcopera.clases;

public class VentaProducto {
    String id, idProducto,idUsuario,fecha;
    double precioOriginal,precioFinal;
    int cantidad;

    public VentaProducto( String idProducto, String idUsuario, String fecha, double precioOriginal, double precioFinal, int cantidad) {
        this.id = "";
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.precioOriginal = precioOriginal;
        this.precioFinal = precioFinal;
        this.cantidad = cantidad;
    }
    public VentaProducto() {
        this.id = "";
        this.idProducto = "";
        this.idUsuario = "";
        this.fecha = "";
        this.precioOriginal = 0;
        this.precioFinal = 0;
        this.cantidad = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
