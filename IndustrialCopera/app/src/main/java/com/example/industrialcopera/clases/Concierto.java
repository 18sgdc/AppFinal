package com.example.industrialcopera.clases;

public class Concierto {
    private String id, artista,fecha,hora,idFotos;
    private double precio;
    private int vendidas,aforo;

    public Concierto(String artista, String fecha, String hora, String idFotos, double precio, int vendidas, int aforo) {
        this.id = "";
        this.artista = artista;
        this.fecha = fecha;
        this.hora = hora;
        this.idFotos = idFotos;
        this.precio = precio;
        this.vendidas = vendidas;
        this.aforo = aforo;
    }

    public Concierto() {
        this.id = "";
        this.artista = "";
        this.fecha = "";
        this.hora = "";
        this.idFotos = "";
        this.precio = 0;
        this.vendidas = 0;
        this.aforo = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdFotos() {
        return idFotos;
    }

    public void setIdFotos(String idFotos) {
        this.idFotos = idFotos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVendidas() {
        return vendidas;
    }

    public void setVendidas(int vendidas) {
        this.vendidas = vendidas;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
}
