package com.esau.poliagenda.Objetos.Materia;

public class Referencias {
    private String Autor;
    private String Editorial;

    private String Titulo;

    public Referencias() {
    }

    public Referencias(String autor, String editorial, String titulo) {
        Autor = autor;
        Editorial = editorial;
        Titulo = titulo;
    }
    public String getAutor() {
        return Autor;
    }
    public void setAutor(String autor) {
        Autor = autor;
    }
    public String getEditorial() {
        return Editorial;
    }
    public void setEditorial(String editorial) {
        Editorial = editorial;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

}
