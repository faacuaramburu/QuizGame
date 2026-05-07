package edu.ucu.Game;

import edu.ucu.models.*;
import edu.ucu.tda.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuizGame juego = new QuizGame(sc);

        juego.cargarDatosEjemplo();

        int opcion;
        do {
            juego.mostrarMenu();
            opcion = leerInt(sc);

            switch (opcion) {
                case 1:
                    juego.iniciarPartida();
                    break;
                case 2:
                    System.out.print("ID: ");
                    int id = leerInt(sc);
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    juego.registrarJugador(id, nombre);
                    break;
                case 3:
                    System.out.print("ID pregunta: ");
                    int idP = leerInt(sc);
                    System.out.print("Enunciado: ");
                    String enunciado = sc.nextLine();
                    String[] ops = new String[4];
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Opción " + (i+1) + ": ");
                        ops[i] = sc.nextLine();
                    }
                    System.out.print("Correcta (1-4): ");
                    int correcta = leerInt(sc) - 1;
                    System.out.print("Categoría: ");
                    String cat = sc.nextLine();
                    juego.registrarPregunta(idP, enunciado, ops, correcta, cat);
                    break;
                case 4:
                    System.out.print("ID a eliminar: ");
                    int idE = leerInt(sc);
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
                        idHistorial = leerInt(sc);

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
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 9);

        sc.close();
        // iba a agregar un resumen de estadísticas por categoría al final pero no me dio el tiempo
    }

    private static int leerInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, ingrese un número.");
            return -1;
        }
    }
}
