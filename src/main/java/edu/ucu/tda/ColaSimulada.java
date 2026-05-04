package edu.ucu.tda;
import java.util.Comparator;
import java.util.List;

import edu.ucu.tda.TDACola;
import edu.ucu.tda.ListaSimulada;

public class ColaSimulada <T extends Comparable <T>> extends ListaSimulada <T> implements TDACola<T>{

    @Override
    public T frente(){
        if (esVacio()){
            return null;
        }
        return cabeza.dato;
    }

    @Override
    public T quitaDeCola(){
        if (esVacio()){
            return null;
        }
        T primero = cabeza.dato;
        cabeza = cabeza.siguiente;
        tamaño--;
        return primero;
    }

    @Override
    public boolean poneEnCola(T elemento){
        if (elemento == null){
            return false;
        }
        agregar(elemento);
        return true;
    }
}