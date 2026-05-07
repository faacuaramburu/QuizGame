package edu.ucu;
import edu.ucu.Game.QuizGame;
import edu.ucu.models.Jugador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRegistrarJugador {
    private QuizGame juego;

    @Before
    public void setUp() {
        juego = new QuizGame();
    }

    @Test
    public void TestRegistrarJugadorExitoso() {
        boolean resultado = juego.registrarJugador(1, "Rodrigo");

        assertTrue("El registro del jugador fue exitoso", resultado);

        Jugador encontrado = juego.buscarJugador(1);
        assertNotNull("El jugador deberia existir despues de buscarlo", encontrado);
        assertEquals("El nombre deberia coincidir", "Rodrigo", encontrado.getNombre());
        encontrado.sumarPuntos(10);
        assertEquals("el numero de puntos del jugador encontrado debe ser de 10", 10, encontrado.getPuntajeActual());
        assertEquals("El id del jugador deberia ser 1", 1, encontrado.getIdJugador());
    }

    @Test
    public void TestJugadorDuplicado() {
        juego.registrarJugador(1, "Rodrigo");
        boolean resultado = juego.registrarJugador(1, "Emilia");
        assertFalse("No se deberia poder registrar 2 jugadores con un mismo id", resultado);
    }

    @Test
    public void TestBuscarJugadorInexistente() {
        Jugador encontrado = juego.buscarJugador(1231231);
        assertNull("No deberia encontrar un jugador que su id no exista", encontrado);
    }

    @Test
    public void TestJugadorPuntosNegativos() {
        juego.registrarJugador(5, "Jon jones");
        Jugador encontrado = juego.buscarJugador(5);
        encontrado.sumarPuntos(-5);
        assertFalse("No deberia ocurrir nunca que los puntos sean negativos", encontrado.getPuntajeActual() < 0);
    }

    @Test
    public void TestRegistrarJugadorConNombreVacio() {
        boolean resultado = juego.registrarJugador(9, "");
        assertFalse("No deberia poder registrarse un jugador sin nombre", resultado);
    }
    @Test
    public void TestRegistrarJugadorConIdNegativo() {
        boolean resultado = juego.registrarJugador(-1, "Nombre");
        assertFalse("No deberia poder registrarse un jugador con id negativo", resultado);
    }
    @Test
    public void TestAbrirHistorialSinJugadoresRegistrados()
    {
        juego.verHistorialJugador(999);
        // No se lanza ninguna excepcion, el mensaje de error se muestra en consola  
        assertTrue("Se intento ver el historial de un jugador que no existe", true);                                                   
    
    }
    @Test 
    public void TestJugadoresYNoPreguntas()
    {
        juego.registrarJugador(2, "Rodrigo");
        juego.registrarJugador(4, "Aura");
        juego.iniciarPartida();
        // No se lanza ninguna excepcion, el mensaje de error se muestra en consola
        assertTrue("Se intento iniciar una partida sin preguntas registradas", true);
    }
    
}