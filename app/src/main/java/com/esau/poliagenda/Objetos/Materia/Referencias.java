package com.esau.poliagenda.Objetos.Materia;

public class Referencias {
    private String autor;
    private String editorial;

    private String titulo;

    public Referencias() {
    }

    public  Referencias(String autor, String editorial, String titulo) {
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
