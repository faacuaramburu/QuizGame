package edu.ucu;

import edu.ucu.Game.QuizGame;
import edu.ucu.models.Pregunta;
import org.junit.Test;
import static org.junit.Assert.*;

public class PreguntasTest {

    @Test
    public void preguntaDuplicada(){
        QuizGame juego = new QuizGame();

        String[] opciones1 = {"A", "B", "C", "D"};
        String[] opciones2 = {"Uno", "Dos", "Tres", "Cuatro"};

        boolean primera = juego.registrarPregunta(
                101,
                "¿Cuál es la capital de España?",
                opciones1,
                0,
                "Geografía"
        );

        boolean duplicada = juego.registrarPregunta(
                101,
                "Otra pregunta con el mismo ID",
                opciones2,
                1,
                "Historia"
        );

        assertTrue(primera);
        assertFalse(duplicada);
    }

    @Test
    public void laPreguntaSeCargaEnLaCola() {
        QuizGame juego = new QuizGame();

        String[] opciones = {"A", "B", "C", "D"};

        juego.registrarPregunta(
                1,
                "Pregunta de prueba",
                opciones,
                0,
                "General"
        );

        juego.cargarPreguntasPendientes();

        assertTrue(juego.getPreguntasPendientes().tamaño() > 0);
    }

    @Test
    public void noHayPreguntasRegistradas() {
        QuizGame juego = new QuizGame();

        juego.cargarPreguntasPendientes();

        assertEquals(0, juego.getPreguntasPendientes().tamaño());
    }

    @Test
    public void laPreguntaEnColaNoDebeSerVacia() {

        QuizGame juego = new QuizGame();

        String[] opciones = {"A", "B", "C", "D"};

        juego.registrarPregunta(
                1,
                "Pregunta de prueba",
                opciones,
                0,
                "General"
        );

        juego.cargarPreguntasPendientes();

        Pregunta pregunta = juego.getPreguntasPendientes().quitaDeCola();

        assertNotNull(pregunta);
        assertNotNull(pregunta.getEnunciado());
        assertFalse(pregunta.getEnunciado().trim().isEmpty());
    }

    @Test
    public void eliminarPreguntaDeLaCola(){
        QuizGame juego = new QuizGame();

        String[] opciones = {"A", "B", "C", "D"};

        boolean registrada = juego.registrarPregunta(
                10,
                "Pregunta a eliminar",
                opciones,
                1,
                "General"
        );

        boolean eliminada = juego.eliminarPregunta(10);

        boolean eliminarOtraVez = juego.eliminarPregunta(10);

        assertTrue(registrada);
        assertTrue(eliminada);
        assertFalse(eliminarOtraVez);
    }
}