package edu.ucu;
import edu.ucu.Game.QuizGame;
import edu.ucu.models.Jugador;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.beans.Transient;


public class TestRegistrarJugador {
         private QuizGame juego;

        @Before
        public void setUp()
        {
            juego = new QuizGame();
        }
    @Test
    public void TestRegistrarJugadorExitoso()
    {
        boolean resultado = juego.registrarJugador(1, "Rodrigo");


        assertTrue("El registro del jugador fue exitoso", resultado);

        //verificar que el jugador existe
        Jugador encontrado = juego.buscarJugador(1);
        assertNotNull("El jugador deberia existir despues de buscarlo", enontrado);
        assertTrue("El nombre deberia coincidir", "Rodrigo", encontrado.getNombre());
        encontrado.sumarPuntos(10);
        asserTrue("el numero de puntos del jugador encontrado debe ser de 10", encontrado.getPuntajeActual());
        asserTrue("El id del jugador deberia ser 1", encotrado.getIdJugador());

    }
    @Test
    public void TestJugadorDuplicado()
    {
        juego.registrarJugador(1, "Rodrigo");
        boolean resultado = juego.registrarJugador(1, "Emilia");
        //assert false
        assertFalse("No se deberia poder registrar 2 jugadores con un mismo id", resultado);
        
    }
    @Test
    public void TestBuscarJugadorInexistente()
    {
        Jugador encontrado = juego.buscarJugador(1231231);
        assertNull("No deberia encontrar un jugador que  su id no exista", encontrado);
    }
    @Test
    public void TestJugadorPuntosNegativos()
    {
        Jugador encontrado = juego.registrarJugador(5, "Jon jones");
        encontrado.sumarPuntos(-5);
        assertFalse("No deberia ocurrir nunca que los puntos sean negativos", encontrado.getPuntajeActual());

    }
    @Test
    public void TestRegistrarJugadorConNombreVacio()
    {
        Jugador encontrado = juego.registrarJugador(9, "");
        encontrado.buscarJugador(9);
        assertFalse("No deberia encontrarse jugadores sin nombre", encontrado);

    }
}
