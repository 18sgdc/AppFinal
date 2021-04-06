package com.example.industrialcopera.clases;

public class Usuario {
    private String nombre,contraseña,correo,id,url;
    private boolean admin,foto;

    public Usuario(String nombre, String contraseña,String correo, boolean admin,boolean foto) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.admin = admin;
        this.foto = foto;
        this.url = "";
        this.id = "";
    }
    public Usuario() {
        this.nombre = "";
        this.contraseña = "";
        this.correo = "";
        this.admin = false;
        this.foto = false;
        this.url = "";
        this.id = "";
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
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
}
