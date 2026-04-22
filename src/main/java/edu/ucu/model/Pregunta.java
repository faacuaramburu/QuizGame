package edu.ucu.model;

public class Pregunta {

    private int idPregunta;
    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta;
    private String categoria;
    
    public Pregunta(int idPregunta, String enunciado, String[] opciones, int respuestaCorrecta, String categoria) {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.categoria = categoria;
    }

    // devuelve el id de la pregunta
    public int getIdPregunta() {
        return idPregunta;
    }

    // devuelve el enunciado de la pregunta
    public String getEnunciado() {
        return enunciado;
    }

    // devuelve el array de opciones
    public String[] getOpciones() {
        return opciones;
    }

    // devuelve el índice de la respuesta correcta
    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    // devuelve la categoría de la pregunta
    public String getCategoria() {
        return categoria;
    }

    // recibe la opción elegida por el jugador y devuelve true si es correcta
    public boolean esCorrecta(int respuesta) {
        return respuesta == respuestaCorrecta;
    }

    // muestra la pregunta con todas sus opciones numeradas
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + idPregunta + "] " + enunciado + "\n");
        for (int i = 0; i < opciones.length; i++) {
            sb.append("  " + (i + 1) + ". " + opciones[i] + "\n");
        }
        sb.append("  Categoría: " + categoria);
        return sb.toString();
    }
}