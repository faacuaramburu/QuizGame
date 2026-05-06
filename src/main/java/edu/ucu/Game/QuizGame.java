package edu.ucu.Game;

import edu.ucu.models.*;
import edu.ucu.tda.*;
import java.util.Scanner;

public class QuizGame {
    
    // ========== ESTRUCTURAS ==========
    private ColaCircular turnos;                           // cola circular para ids
    private ColaSimulada<Pregunta> preguntasPendientes;    // cola simulada
    private ABBImpl<DatoHistorial> historial;              // ABB
    private ListaSimulada<Jugador> jugadoresRegistrados;   // lista enlazada
    private ListaSimulada<Pregunta> preguntasRegistradas;  // lista enlazada
    private PilaListaSimulada<Respuesta> pilaDeshacer;     // pila
    private Scanner scanner;
    
    private static class DatoHistorial implements Comparable<DatoHistorial> {
        private int idJugador;
        private ListaSimulada<Respuesta> respuestas;
        
        public DatoHistorial(int idJugador) {
            this.idJugador = idJugador;
            this.respuestas = new ListaSimulada<>();
        }
        
        public int getIdJugador() { return idJugador; }
        public ListaSimulada<Respuesta> getRespuestas() { return respuestas; }
        
        public void agregarRespuesta(Respuesta r) {
            respuestas.agregar(r);  
        }
        
        @Override
        public int compareTo(DatoHistorial otro) {
            return Integer.compare(this.idJugador, otro.idJugador);
        }
    }
    
    // ========== CONSTRUCTOR ==========
    public QuizGame() {
        this.jugadoresRegistrados = new ListaSimulada<>();
        this.preguntasRegistradas = new ListaSimulada<>();
        this.historial = new ABBImpl<>();
        this.pilaDeshacer = new PilaListaSimulada<>();
        this.scanner = new Scanner(System.in);
    }
    
    // ========== GESTIÓN DE DATOS ==========
    
    public boolean registrarJugador(int id, String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacio");
            return false;
        }
        if (buscarJugador(id) != null) {
            System.out.println("Error: Ya existe un jugador con ID " + id);
            return false;
        }
        Jugador nuevo = new Jugador(id, nombre);
        jugadoresRegistrados.agregar(nuevo);  
        System.out.println("Jugador " + nombre + " registrado.");
        return true;
    }
    
    public Jugador buscarJugador(int id) {
        if (jugadoresRegistrados.esVacio()) return null; 
        
        for (int i = 0; i < jugadoresRegistrados.tamaño(); i++) { 
            Jugador j = jugadoresRegistrados.obtener(i);  
            if (j.getIdJugador() == id) return j;
        }
        return null;
    }
    
    public boolean registrarPregunta(int id, String enunciado, String[] opciones, 
                                      int respuestaCorrecta, String categoria) {
        if (buscarPregunta(id) != null) {
            System.out.println("Error: Ya existe una pregunta con ID " + id);
            return false;
        }
        Pregunta nueva = new Pregunta(id, enunciado, opciones, respuestaCorrecta, categoria);
        preguntasRegistradas.agregar(nueva);  
        System.out.println("Pregunta registrada.");
        return true;
    }
    
    public Pregunta buscarPregunta(int id) {
        if (preguntasRegistradas.esVacio()) return null;
        
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            Pregunta p = preguntasRegistradas.obtener(i);
            if (p.getIdPregunta() == id) return p;
        }
        return null;
    }
    
    public boolean eliminarPregunta(int id) {
        Pregunta aEliminar = buscarPregunta(id);
        if (aEliminar == null) {
            System.out.println("Error: No existe pregunta con ID " + id);
            return false;
        }
        
        // Buscar la posición y usar remover
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            Pregunta p = preguntasRegistradas.obtener(i);
            if (p.getIdPregunta() == id) {
                preguntasRegistradas.remover(i);  
                System.out.println("Pregunta eliminada.");
                return true;
            }
        }
        return false;
    }
    
    // ========== PREPARACIÓN DEL JUEGO ==========
    
    public void inicializarTurnos() {
        int numJugadores = jugadoresRegistrados.tamaño();
        if (numJugadores == 0) {
            System.out.println("No hay jugadores registrados.");
            return;
        }
        
        turnos = new ColaCircular(numJugadores);  
        
        for (int i = 0; i < numJugadores; i++) {
            Jugador j = jugadoresRegistrados.obtener(i);
            turnos.agregarACola(j.getIdJugador());  
        }
        System.out.println("Turnos inicializados con " + numJugadores + " jugadores.");
    }
    
    public void cargarPreguntasPendientes() {
        preguntasPendientes = new ColaSimulada<>();
        
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            Pregunta p = preguntasRegistradas.obtener(i);
            preguntasPendientes.poneEnCola(p);  
        }
        System.out.println("Preguntas cargadas: " + preguntasPendientes.tamaño());
    }
    
    // ========== FLUJO DEL JUEGO ==========
    
    public void iniciarPartida() {
        if (jugadoresRegistrados.esVacio()) {
            System.out.println("Error: No hay jugadores registrados.");
            return;
        }
        if (preguntasRegistradas.esVacio()) {
            System.out.println("Error: No hay preguntas registradas.");
            return;
        }
        
        pilaDeshacer = new PilaListaSimulada<>();  // nueva pila vacía
        inicializarTurnos();
        cargarPreguntasPendientes();
        
        System.out.println("\n=== INICIO DE LA PARTIDA ===");
        
        int ronda = 1;
        int puntosPorAcierto = 10;
        
        while (preguntasPendientes.tamaño() > 0) {
            System.out.println("\n--- RONDA " + ronda + " ---");
            
            // Sacar jugador de la cola circular
            int idJugadorActual = turnos.quitarDeCola();  
            Jugador jugadorActual = buscarJugador(idJugadorActual);
            
            // Sacar pregunta
            Pregunta preguntaActual = preguntasPendientes.quitaDeCola();  
            
            System.out.println("Turno de: " + jugadorActual.getNombre());
            System.out.println(preguntaActual);
            
            int respuestaUsuario = leerRespuesta();
            boolean esCorrecta = preguntaActual.verificarRespuesta(respuestaUsuario - 1);
            
            Respuesta r = new Respuesta(jugadorActual.getIdJugador(), 
                                        preguntaActual.getIdPregunta(), 
                                        esCorrecta);
            
            guardarEnHistorial(jugadorActual.getIdJugador(), r);
            pilaDeshacer.mete(r);  
            
            if (esCorrecta) {
                jugadorActual.sumarPuntos(puntosPorAcierto);
                System.out.println("¡Correcta! +" + puntosPorAcierto + " puntos.");
            } else {
                int correctaIndex = preguntaActual.getRespuestaCorrecta();
                System.out.println("Incorrecta. La respuesta correcta era: " + 
                                   preguntaActual.getOpciones()[correctaIndex]);
            }
            
            System.out.println("Puntaje actual: " + jugadorActual.getPuntajeActual());
            
            // Volver a encolar
            turnos.agregarACola(jugadorActual.getIdJugador()); 
            
            ronda++;
        }
        
        System.out.println("\n=== FIN DE LA PARTIDA ===");
        mostrarResultados();
    }
    
    private void guardarEnHistorial(int idJugador, Respuesta r) {
        DatoHistorial busqueda = new DatoHistorial(idJugador);
        DatoHistorial existente = historial.buscar(busqueda);  
        
        if (existente != null) {
            existente.agregarRespuesta(r);
        } else {
            DatoHistorial nuevo = new DatoHistorial(idJugador);
            nuevo.agregarRespuesta(r);
            historial.insertar(nuevo);  
        }
    }
    
    private ListaSimulada<Respuesta> getRespuestasJugador(int idJugador) {
        DatoHistorial busqueda = new DatoHistorial(idJugador);
        DatoHistorial encontrado = historial.buscar(busqueda);
        return encontrado != null ? encontrado.getRespuestas() : new ListaSimulada<>();
    }
    
    private int getPuntajeDesdeHistorial(int idJugador) {
        ListaSimulada<Respuesta> respuestas = getRespuestasJugador(idJugador);
        int total = 0;
        for (int i = 0; i < respuestas.tamaño(); i++) {
            Respuesta r = respuestas.obtener(i);
            if (r.isCorrecta()) total += 10;
        }
        return total;
    }
    
    private int leerRespuesta() {
        int opcion = 0;
        while (opcion < 1 || opcion > 4) {
            System.out.print("Ingrese su respuesta (1-4): ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        return opcion;
    }
    
    // ========== CONSULTAS ==========
    
    public void mostrarResultados() {
        System.out.println("\n=== RESULTADOS FINALES ===");
        System.out.println("-----------------------------------");
        
        for (int i = 0; i < jugadoresRegistrados.tamaño(); i++) {
            Jugador j = jugadoresRegistrados.obtener(i);
            int puntajeHistorial = getPuntajeDesdeHistorial(j.getIdJugador());
            j.setPuntajeActual(puntajeHistorial);
            
            System.out.println(j.getNombre() + " (ID: " + j.getIdJugador() + ")");
            System.out.println("  Puntaje total: " + j.getPuntajeActual());
            System.out.println("-----------------------------------");
        }
        
        Jugador ganador = getGanador();
        if (ganador != null) {
            System.out.println("🏆 ¡GANADOR: " + ganador.getNombre() + 
                               " con " + ganador.getPuntajeActual() + " puntos! 🏆");
        }
    }
    
    public Jugador getGanador() {
        if (jugadoresRegistrados.esVacio()) return null;
        
        Jugador ganador = jugadoresRegistrados.obtener(0);
        for (int i = 1; i < jugadoresRegistrados.tamaño(); i++) {
            Jugador j = jugadoresRegistrados.obtener(i);
            if (j.getPuntajeActual() > ganador.getPuntajeActual()) {
                ganador = j;
            }
        }
        return ganador;
    }
    
    // ========== DESHACER ==========
    
    public boolean deshacerUltimaRespuesta() {
        if (pilaDeshacer.esVacio()) {  
            System.out.println("No hay respuestas para deshacer.");
            return false;
        }
        
        Respuesta ultima = pilaDeshacer.saca();  
        
        if (ultima.isCorrecta()) {
            Jugador jugador = buscarJugador(ultima.getIdJugador());
            if (jugador != null) {
                jugador.restarPuntos(10);
                System.out.println("Se restaron 10 puntos a " + jugador.getNombre());
            }
        }
        
        System.out.println("Última respuesta deshecha.");
        return true;
    }
    
    // ========== DATOS DE EJEMPLO ==========
    
    public void cargarDatosEjemplo() {
        System.out.println("\n--- Cargando datos de ejemplo ---");
        
        registrarJugador(1, "Ana");
        registrarJugador(2, "Luis");
        registrarJugador(3, "Rodrigo");
        registrarJugador(4, "Maria");
        
        String[] opciones1 = {"Madrid", "Barcelona", "Valencia", "Sevilla"};
        registrarPregunta(101, "¿Cuál es la capital de España?", opciones1, 0, "Geografía");
        
        String[] opciones2 = {"10", "11", "12", "13"};
        registrarPregunta(102, "¿Cuántos jugadores tiene un equipo de fútbol?", opciones2, 2, "Deportes");
        
        String[] opciones3 = {"1999", "2000", "2001", "2002"};
        registrarPregunta(103, "¿En qué año comenzó el siglo XXI?", opciones3, 1, "Historia");
        
        System.out.println("Datos cargados.\n");
    }
    
    // ========== MENÚ ==========
    
    public void mostrarMenu() {
        System.out.println("\n=== QUIZ GAME ===");
        System.out.println("1. Jugar partida");
        System.out.println("2. Registrar jugador");
        System.out.println("3. Registrar pregunta");
        System.out.println("4. Eliminar pregunta");
        System.out.println("5. Ver jugadores");
        System.out.println("6. Ver preguntas");
        System.out.println("7. Deshacer última respuesta");
        System.out.println("8. Salir");
        System.out.print("Opción: ");
    }
    
    public void verJugadores() {
        System.out.println("\n=== JUGADORES ===");
        if (jugadoresRegistrados.esVacio()) {
            System.out.println("No hay jugadores.");
            return;
        }
        for (int i = 0; i < jugadoresRegistrados.tamaño(); i++) {
            System.out.println("  " + jugadoresRegistrados.obtener(i));
        }
    }
    
    public void verPreguntas() {
        System.out.println("\n=== PREGUNTAS ===");
        if (preguntasRegistradas.esVacio()) {
            System.out.println("No hay preguntas.");
            return;
        }
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            Pregunta p = preguntasRegistradas.obtener(i);
            System.out.println("  ID: " + p.getIdPregunta() + " | " + p.getEnunciado());
        }
    }
    
}