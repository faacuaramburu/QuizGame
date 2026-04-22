package edu.ucu.model;

public class Jugador {

    private int idJugador;
    private String nombre;
    private int puntajeActual;

    // crea un jugador nuevo con puntaje 0
    public Jugador(int idJugador, String nombre) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.puntajeActual = 0;
    }

    // devuelve el id del jugador
    public int getIdJugador() {
        return idJugador;
    }

    // devuelve el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    // devuelve el puntaje actual del jugador
    public int getPuntajeActual() {
        return puntajeActual;
    }

    // suma un punto cuando el jugador responde correctamente
    public void sumarPunto() {
        this.puntajeActual++;
    }

    // representación en texto del jugadooorrrrr
    @Override
    public String toString() {
        return nombre + " (id=" + idJugador + ", puntaje=" + puntajeActual + ")";
    }
}