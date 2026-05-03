package edu.ucu.models;

public class Respuesta {
    private int IdJugador;
    private int idPregunta;
    private boolean correcta;
    private long timestamp;

    public Respuesta(int IdJugador, int idPregunta, boolean correcta )
    {
        this.IdJugador = idJugador;
        this.idPregunta = idPregunta;
        this.correcta = correcta;
        this.timestamp = System.currentTimeMillis();
    }
    public int getIdJugador()
    {
        return IdJugador;
    }
    public int getIdJugador()
    {
        return idPregunta;
    }
    public boolean isCorrecta()
    {
        return correcta;
    }
    public long getTimestamp() { return timestamp; }
}
