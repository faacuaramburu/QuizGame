package edu.ucu;

import edu.ucu.Game.QuizGame;
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
}
