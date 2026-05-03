package edu.ucu.models;

public class Jugador 
{
    private int IdJugador;
    private String nombre;
    private int puntajeActual;

    public Jugador(int IdJugador, string nombre)
    {
        this.IdJugador = IdJugador;
        this.nombre = nombre;
        this.puntajeActual = 0;
    }
    public int getIdJugador()
    {
        return IdJugador;
    }
    public String getNombre()
    {
        return nombre;
    }
    public int GetPuntajeActual()
    {
        return puntajeActual;
    }
    public void sumarPuntos(int puntos)
    {
        this.puntajeActual += puntos;
    }
    public void restarPuntos(int puntos)
    {
        this.puntajeActual -= puntos;
    }
    public void setPuntajeActual(int puntajeActual)
    {
        this.puntajeActual = puntajeActual;
    }
    @Override
    public String toString() {
        return nombre + " (ID: " + idJugador + ") - Puntaje: " + puntajeActual;
    }

}