package com.esau.poliagenda.L_Horario;

public class Horario {
    public String dia;
    public String hora;

    public Horario() {
        // Requerido por Firebase
    }

    public Horario(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
}
