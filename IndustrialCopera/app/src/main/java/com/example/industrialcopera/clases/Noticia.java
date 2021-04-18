package com.example.industrialcopera.clases;

public class Noticia {
    private String id, idReferente, titulo, descripcion, idFotos;

    public Noticia(String idReferente, String titulo, String descripcion, String idFotos) {
        this.id = "";
        this.idReferente = idReferente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idFotos = idFotos;
    }

    public Noticia() {
        this.id = "";
        this.idReferente = "";
        this.titulo = "";
        this.descripcion = "";
        this.idFotos = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdReferente() {
        return idReferente;
    }

    public void setIdReferente(String idReferente) {
        this.idReferente = idReferente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdFotos() {
        return idFotos;
    }

    public void setIdFotos(String idFotos) {
        this.idFotos = idFotos;
    }
}
