package edu.ucu.models;

public class Respuesta implements Comparable<Respuesta> {
    private int IdJugador;
    private int idPregunta;
    private boolean correcta;
    private long timestamp;

    public Respuesta(int IdJugador, int idPregunta, boolean correcta )
    {
        this.IdJugador = IdJugador;
        this.idPregunta = idPregunta;
        this.correcta = correcta;
        this.timestamp = System.currentTimeMillis();
    }
    public int getIdJugador()
    {
        return IdJugador;
    }
    public int getIdPregunta()
    {
        return idPregunta;
    }
    public boolean isCorrecta()
    {
        return correcta;
    }
    public long getTimestamp() { return timestamp; }

    @Override
    public int compareTo(Respuesta otra) {
        return Long.compare(this.timestamp, otra.timestamp);
    }
}
