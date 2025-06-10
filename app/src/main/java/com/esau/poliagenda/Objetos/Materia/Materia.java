package com.esau.poliagenda.Objetos.Materia;

public class Materia {
    private Horario horario1;
    private Horario horario2;

    private  String nombre;

    private String profesor;

    private Referencias referencias;

    private  String secuencia;

    public Materia() {
    }

    public Materia(Horario horario1, Horario horario2, String nombre, String profesor, Referencias referencias, String secuencia) {
        this.horario1 = horario1;
        this.horario2 = horario2;
        this.nombre = nombre;
        this.profesor = profesor;
        this.referencias = referencias;
        this.secuencia = secuencia;
    }

    public Horario getHorario1() {
        return horario1;
    }

    public void setHorario1(Horario horario1) {
        this.horario1 = horario1;
    }

    public Horario getHorario2() {
        return horario2;
    }

    public void setHorario2(Horario horario2) {
        this.horario2 = horario2;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getProfesor() {
        return profesor;
    }
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
    public Referencias getReferencias() {
        return referencias;
    }
    public void setReferencias(Referencias referencias) {
        this.referencias = referencias;
    }
    public String getSecuencia() {
        return secuencia;
    }
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

}
