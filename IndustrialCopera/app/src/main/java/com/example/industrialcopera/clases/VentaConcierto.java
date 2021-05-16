package com.example.industrialcopera.clases;

public class VentaConcierto {
    String id, idConcierto,idUsuario,fecha;
    double precioOriginal,precioFinal;

    public VentaConcierto(String idConcierto, String idUsuario, String fecha,double precioOriginal,double precioFinal) {
        this.id = "";
        this.idConcierto = idConcierto;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.precioOriginal = precioOriginal;
        this.precioFinal = precioFinal;
    }
    public VentaConcierto() {
        this.id = "";
        this.idConcierto = "";
        this.idUsuario = "";
        this.fecha = "";
        this.precioOriginal = 0;
        this.precioFinal = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(String idConcierto) {
        this.idConcierto = idConcierto;
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
}
