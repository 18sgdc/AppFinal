package com.example.industrialcopera.clases;

public class Usuario {
    private String id,nombre,contraseña,correo,direccion,tarjeta, urlFoto;
    private boolean admin,foto;
    private int puntosConciertos,puntosProductos,gastablesConcierto,gastablesProductos;

    public Usuario(String nombre, String contraseña,String correo, boolean admin,boolean foto
            ,String direccion, String tarjeta ,int puntosConciertos,int puntosProductos
            ,int gastablesConcierto,int gastablesProductos) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.admin = admin;
        this.foto = foto;
        this.urlFoto = "";
        this.id = "";
        this.direccion=direccion;
        this.tarjeta=tarjeta;
        this.puntosConciertos=puntosConciertos;
        this.puntosProductos=puntosProductos;
        this.gastablesConcierto=gastablesConcierto;
        this.gastablesProductos=gastablesProductos;
    }
    public Usuario() {
        this.nombre = "";
        this.contraseña = "";
        this.correo = "";
        this.admin = false;
        this.foto = false;
        this.urlFoto = "";
        this.id = "";
        this.direccion="";
        this.tarjeta="";
        this.puntosConciertos=0;
        this.puntosProductos=0;
        this.gastablesConcierto=0;
        this.gastablesProductos=0;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrlFoto() {
        return urlFoto;
    }
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isFoto() {
        return foto;
    }

    public void setFoto(boolean foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public int getPuntosConciertos() {
        return puntosConciertos;
    }

    public int getPuntosProductos() {
        return puntosProductos;
    }

    public int getGastablesConcierto() {
        return gastablesConcierto;
    }

    public int getGastablesProductos() {
        return gastablesProductos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void changePuntosConciertos(int cambio) {
        this.puntosConciertos += cambio;
    }

    public void changePuntosProductos(int cambio) {
        this.puntosProductos += cambio;
    }

    public void changeGastablesConcierto(int cambio) {
        this.gastablesConcierto += cambio;
    }

    public void changeGastablesProductos(int cambio) {
        this.gastablesProductos += cambio;
    }
}
