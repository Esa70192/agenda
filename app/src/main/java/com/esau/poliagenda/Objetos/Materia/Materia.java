package com.esau.poliagenda.Objetos.Materia;

public class Materia {
    private Horario Horario1;

    private Horario Horario2;

    private  String Nombre;

    private String Profesor;

    private Referencias Referencias;

    private  String Secuencia;

    public Materia() {
    }



    public Horario getHorario1() {
        return Horario1;
    }

    public void setHorario1(Horario horario1) {
        Horario1 = horario1;
    }

    public Horario getHorario2() {
        return Horario2;
    }

    public void setHorario2(Horario horario2) {
        Horario2 = horario2;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public Referencias getReferencias() {
        return Referencias;
    }
    public void setReferencias(Referencias referencias) {

        Referencias = referencias;
    }

    public String getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(String secuencia) {
        Secuencia = secuencia;
    }




}
