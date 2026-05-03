package edu.ucu.models;

import java.util.Arrays;

import edu.ucu.TDA;

public class Pregunta {
    private int idPregunta;
    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta
    private String categoria;

    public Pregunta(int idPregunta, String enunciado, String[] opciones, int respuestaCorrecta, String categoria)
    {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.opciones = Arrays.copyOf(opciones, opciones.length);
        this.respuestaCorrecta = respuestaCorrecta;
        this.categoria = categoria;
    }
    public int getIdPregunta()
    {
        return idPregunta;
    }
    public String getEnunciado()
    {
        return enunciado;

    }
    public String[] getOpcione()
    {
        return Arrays.copyOf(opciones, opciones.length);
    }
    public int respuestaCorrecta()
    {
        return respuestaCorrecta;
    }
    public String categoria()
    {
        return categoria;
    }
    public boolean VerificarRespuesta(int indiceRespuesta)
    {
        return indiceRespuesta == respuestaCorrecta;
    }
    @Override
    public String toString()
     {
        StringBuilder sb = new StringBuilder();
        sb.append(idPregunta).append(". ").append(enunciado).append("\n");
        for (int i = 0; i < opciones.length; i++) {
            sb.append("   ").append(i+1).append(". ").append(opciones[i]).append("\n");
        }
        return sb.toString();
     }
}
