package edu.ucu.tda;

public class NodoLista<T> {
    protected T dato;  // Cambiado Object por T
    protected NodoLista<T> siguiente;  // Agregado <T> para tipo genérico

    public NodoLista(T dato) {  // Cambiado Object por T
        this.dato = dato;
        this.siguiente = null;
    }
}