package edu.ucu.tda;

public class PilaListaSimulada  < T extends Comparable <T>> extends ListaSimulada <T> implements TDAPila<T> {

    @Override
    public T tope() {                            //Devuelve el elemento del tope
        if (esVacio()) {
            return null;
        }
        return cabeza.dato;
    }

    @Override
    public T saca() {                             //Elimina el elemento del tope y devuelve ese elemento
        if (esVacio()) {
            return null;
        }
        return remover(0);
    }

    @Override
    public void mete(T dato) {                     //Suponemos que la pila esta vacia y agregamos el elemento
        agregar(0, dato);
    }
}