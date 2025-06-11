package com.esau.poliagenda.L_Horario;

public class Materia {
    public String Secuencia;
    public String Nombre_;
    public String Profesor;
    public Horario Horario1;
    public Horario Horario2;

    public Materia() {
        // Constructor vacío necesario para Firebase
    }

    public Materia(String secuencia, String nombre_, String profesor, Horario horario1, Horario horario2) {
        this.Secuencia = secuencia;
        this.Nombre_ = nombre_;
        this.Profesor = profesor;
        this.Horario1 = horario1;
        this.Horario2 = horario2;
    }
}
