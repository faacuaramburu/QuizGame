package edu.ucu.Game;

import edu.ucu.models.*;
import edu.ucu.tda.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuizGame juego = new QuizGame();
        Scanner sc = new Scanner(System.in);

        juego.cargarDatosEjemplo();

        int opcion;
        do {
            juego.mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    juego.iniciarPartida();
                    break;
                case 2:
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    juego.registrarJugador(id, nombre);
                    break;
                case 3:
                    System.out.print("ID pregunta: ");
                    int idP = sc.nextInt(); sc.nextLine();
                    System.out.print("Enunciado: ");
                    String enunciado = sc.nextLine();
                    String[] ops = new String[4];
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Opción " + (i+1) + ": ");
                        ops[i] = sc.nextLine();
                    }
                    System.out.print("Correcta (1-4): ");
                    int correcta = sc.nextInt() - 1; sc.nextLine();
                    System.out.print("Categoría: ");
                    String cat = sc.nextLine();
                    juego.registrarPregunta(idP, enunciado, ops, correcta, cat);
                    break;
                case 4:
                    System.out.print("ID a eliminar: ");
                    int idE = sc.nextInt();
                    juego.eliminarPregunta(idE);
                    break;
                case 5:
                    juego.verJugadores();
                    break;
                case 6:
                    juego.verPreguntas();
                    break;
                case 7:
                    juego.deshacerUltimaRespuesta();
                    juego.mostrarResultados();
                    break;
                case 8:
                    int idHistorial;
                    Jugador jugadorBuscado;

                    do {
                        System.out.print("ID del jugador: ");
                        idHistorial = sc.nextInt();
                        sc.nextLine();

                        jugadorBuscado = juego.buscarJugador(idHistorial);

                        if (jugadorBuscado == null) {
                            System.out.println("Error: No existe un jugador con ese ID. Intente nuevamente.");
                        }

                    } while (jugadorBuscado == null);

                    juego.verHistorialJugador(idHistorial);
                    break;
                case 9:
                    System.out.println("¡Hasta luego!");
                    break;
            }
        } while (opcion != 8);

        sc.close();
        // iba a agregar un resumen de estadísticas por categoría al final pero no me dio el tiempo
    }
}
