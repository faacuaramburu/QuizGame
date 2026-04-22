package edu.ucu.model;

public class Respuesta {

    private Jugador jugador;
    private Pregunta pregunta;
    private int opcionElegida;
    private boolean correcta;

    public Respuesta(Jugador jugador, Pregunta pregunta, int opcionElegida) {
        this.jugador = jugador;
        this.pregunta = pregunta;
        this.opcionElegida = opcionElegida;
        this.correcta = pregunta.esCorrecta(opcionElegida);
    }

    // devuelve el jugador que respondió
    public Jugador getJugador() {
        return jugador;
    }

    // devuelve la pregunta respondida
    public Pregunta getPregunta() {
        return pregunta;
    }

    // devuelve la opción elegida
    public int getOpcionElegida() {
        return opcionElegida;
    }

    // devuelve true si la respuesta fue correcta
    public boolean isCorrecta() {
        return correcta;
    }

   // muestra un resumen de la respuesta
@Override
public String toString() {
    String resultado;
    if (correcta) {
        resultado = "CORRECTO ";
    } else {
        resultado = "INCORRECTO ";
    }
    return jugador.getNombre() + " respondió opción " + opcionElegida + " -> " + resultado;
}
}