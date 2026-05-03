package edu.ucu.tda;

import java.util.function.Consumer;

public class ABBImpl <T extends Comparable<T>> implements TDAArbolBinario<T> {  

    private TDAElemento<T> raiz;
    
    @Override
    public int cantidadHojas(){
        if (raiz != null){
            return raiz.cantidadHojas();
        }
        return 0;
    }

    @Override
    public boolean esVacio(){
        return raiz == null;
    }
        
    @Override
    public TDAElemento<T> obtenerRaiz(){
        return raiz;
    }
    
    @Override
    public int cantidadNodos(){
        if (raiz != null){
            return raiz.cantidadNodos();
        }
        return 0;
    }

    @Override
    public int cantidadNodosInternos(){
        if (raiz != null){
            return raiz.cantidadNodosInternos();
        }
        return 0;
    }

    @Override 
    public T buscar(Comparable<T> predicate) {
        if (raiz != null) {
            TDAElemento<T> resultado = raiz.buscar(predicate);
            if (resultado == null) return null;
            return resultado.getDato();
        } 
        return null;
    }   

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda){
        if (raiz != null){
            raiz = raiz.eliminar(criterioBusqueda);
            return true;
        }
        return false;
    }

    @Override
    public void preOrder(Consumer<T> consumidor){
        if (raiz != null){
            raiz.preOrder(t -> consumidor.accept(t.getDato()));
        }
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (esVacio()){
            this.raiz = new ElementoABBImpl<>((T) dato);
            return true;
        } else {
            return raiz.insertar(dato);
        }
    }
    
    @Override
    public void postOrder(Consumer<T> consumidor){
        if (raiz != null){
            raiz.postOrder(t -> consumidor.accept(t.getDato()));
        }
    }
    
    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (!esVacio()) {
            raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
        }
    }
}