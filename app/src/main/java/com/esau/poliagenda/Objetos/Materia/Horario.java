package com.esau.poliagenda.Objetos.Materia;

public class Horario {
    private String dia;
    private String hora;

    public Horario() {}

    public Horario(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
