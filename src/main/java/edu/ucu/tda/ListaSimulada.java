package edu.ucu.tda;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import edu.ucu.tda.TDALista;

public class ListaSimulada<T> implements TDALista<T> {
    protected NodoLista<T> cabeza;
    protected int tamaño;
    
    public ListaSimulada() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    @Override
    public void agregar(T elem) {
        NodoLista<T> nuevo = new NodoLista<>(elem);
        if (cabeza == null) {
            cabeza = nuevo;
        }
        else {
            NodoLista<T> actual = cabeza;
            while(actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo; // conectar el nuevo nodo al final de la lista
        }
        tamaño++;

    }
    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        NodoLista<T> nuevo = new NodoLista<>(elem);
        if (index == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo ;
        }
        else {
            NodoLista<T> actual = cabeza;
            for (int i = 0;  i< index -1; i++) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
        
    
    }
    @Override
    public T obtener(int index) {
        // validamos que el indice sea valido
        if (index < 0 || index >= tamaño)  {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index); 
        }
        //recorrer desde la cabeza hasta el indice
        
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;

    }
    @Override
    public T remover (int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        T datoremovido;
        
        if (index == 0) {
            datoremovido = cabeza.dato;
            cabeza = cabeza.siguiente;
        } else {
            NodoLista<T> actual = cabeza;
        
            for (int i = 0; i < index - 1; i++) {
                actual = actual.siguiente;
            }
            NodoLista<T> nodoARemover = actual.siguiente;
            datoremovido = nodoARemover.dato;
            actual.siguiente = nodoARemover.siguiente;
        }
        
        tamaño--;
        return datoremovido;
    }
    @Override
    public boolean remover (T elem) {
        
        if (cabeza == null)
        {
            return false;
        }
            

        if (cabeza.dato.equals(elem))
        {
            cabeza = cabeza.siguiente;
            tamaño = tamaño -1;
            return true;
        }
        NodoLista<T> actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(elem)) 
            {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño = tamaño -1;
                return true;
            }
            actual = actual.siguiente;

        }
        return false;

        
    }
    @Override
    public boolean contiene(T elem)
    {
        NodoLista<T> actual = cabeza;
        while (actual != null) 
        {
            if (actual.dato.equals(elem))
            {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;


    }
    @Override
    public int indiceDe(T elem)
    {
        NodoLista<T> actual = cabeza;
        int contadorDePosicion = 0;
        while ( actual != null)
        {
            if (actual.dato.equals(elem))
            {
                return contadorDePosicion;
            }
            actual = actual.siguiente;
            contadorDePosicion ++ ;

        }
        return -1;



    }
    @Override
    public T buscar(Predicate<T> criterio)
    {
    NodoLista<T> actual = cabeza;
    while (actual != null) {
        
        if (criterio.test(actual.dato)) {
            return actual.dato;  
        }
        actual = actual.siguiente;
    }
    return null;  

        
    }
@Override
public TDALista<T> ordenar(Comparator<T> comparator) 
{
    // Crear nueva lista para el resultado
    ListaSimulada<T> listaOrdenada = new ListaSimulada<T>();
    
    // Caso: lista vacía
    if (cabeza == null) {
        return listaOrdenada;
    }
    
    // Usar el tamaño real de la lista (this.tamaño)
    int tam = this.tamaño;  // Cambiar nombre para evitar confusión
    
    // Crear y llenar lista temporal
    List<T> arreglotemp = new ArrayList<>(tam);
    
    NodoLista<T> actual = cabeza;
    for (int i = 0; i < tam; i++) {
        arreglotemp.add(actual.dato);
        actual = actual.siguiente;
    }
    
    // Bubble Sort
    for (int i = 0; i < tam - 1; i++) {
        for (int j = 0; j < tam - 1 - i; j++) {
            // Si el elemento actual es mayor que el siguiente
            if (comparator.compare(arreglotemp.get(j), arreglotemp.get(j + 1)) > 0) {
                // Intercambiar
                T temp = arreglotemp.get(j);
                arreglotemp.set(j, arreglotemp.get(j + 1));
                arreglotemp.set(j + 1, temp);
            }
        }
    }
    
    // Construir lista ordenada
    for (int i = 0; i < tam; i++) {
        listaOrdenada.agregar(arreglotemp.get(i));
    }
    
    return listaOrdenada;
}
    @Override
    public int tamaño()
    {
        return tamaño;

    }
    @Override
    public boolean esVacio()
    {
        return tamaño == 0;
    }
    @Override
    public void vaciar()
    {
        cabeza = null;
        tamaño = 0;
    }
}