package edu.ucu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ColaCircular cola = new ColaCircular(5);
        cola.agregarACola(10);
        cola.agregarACola(20);
        System.out.println("Frente de la cola: " + cola.frente());
        System.out.println("Elemento quitado de la cola: " + cola.quitarDeCola());
        System.out.println("Frente de la cola después de quitar un elemento: " + cola.frente());
    }
}
