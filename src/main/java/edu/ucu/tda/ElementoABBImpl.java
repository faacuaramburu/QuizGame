package edu.ucu.tda;

import java.util.function.Consumer;

public class ElementoABBImpl<T> implements TDAElemento<T> {

    private T dato;
    private TDAElemento<T> hijoIzq;
    private TDAElemento<T> hijoDer;

    public ElementoABBImpl(T dato) {
        this.dato = dato;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzq = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDer = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return this.hijoIzq;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return this.hijoDer;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return this.dato;
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterio) {
        if (criterio.compareTo(this.dato) < 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(criterio);
            }
            return this;
        } else if (criterio.compareTo(this.dato) > 0) {
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(criterio);
            }
            return this;
        }
        return quitarNodo();
    }

    private TDAElemento<T> quitarNodo() {
        if (hijoIzq == null) {
            return hijoDer;
        }
        if (hijoDer == null) {
            return hijoIzq;
        } else {
            TDAElemento<T> elHijo = hijoIzq;
            TDAElemento<T> padreDelHijo = this;
            while (elHijo.getHijoDerecho() != null) {
                padreDelHijo = elHijo;
                elHijo = elHijo.getHijoDerecho();
            }
            if (padreDelHijo != this) {
                padreDelHijo.setHijoDerecho(elHijo.getHijoIzquierdo());
                elHijo.setHijoIzquierdo(hijoIzq);
            }
            elHijo.setHijoDerecho(hijoDer);
            return elHijo;
        }
    }

    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {
        TDAElemento<T> resultado = null;

        if (criterioBusqueda.compareTo(this.dato) == 0) {
            resultado = this;
        } else {
            if (criterioBusqueda.compareTo(this.dato) < 0) {
                if (hijoIzq != null) {
                    resultado = hijoIzq.buscar(criterioBusqueda);
                }
            } else {
                if (hijoDer != null) {
                    resultado = hijoDer.buscar(criterioBusqueda);
                }
            }
        }
        return resultado;
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        if (nuevoDato.compareTo(this.dato) > 0) {
            if (this.hijoDer == null) {
                this.hijoDer = new ElementoABBImpl<>((T) nuevoDato);
                return true;
            } else {
                return this.hijoDer.insertar(nuevoDato);
            }
        } else if (nuevoDato.compareTo(this.dato) < 0) {
            if (this.hijoIzq == null) {
                this.hijoIzq = new ElementoABBImpl<>((T) nuevoDato);
                return true;
            } else {
                return this.hijoIzq.insertar(nuevoDato);
            }
        } else {
            return false;
        }
    }

    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzq != null) {
            hijoIzq.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (hijoDer != null) {
            hijoDer.inOrder(consumidor);
        }
    }

    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (hijoIzq != null) {
            hijoIzq.preOrder(consumidor);
        }
        if (hijoDer != null) {
            hijoDer.preOrder(consumidor);
        }
    }

    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzq != null) {
            hijoIzq.postOrder(consumidor);
        }
        if (hijoDer != null) {
            hijoDer.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    public boolean esHoja() {
        return (hijoIzq == null) && (hijoDer == null);
    }

    public int cantidadHojas() {
        if (esHoja()) {
            return 1;
        }
        int cantidadHojas = 0;
        if (hijoIzq != null) {
            cantidadHojas += hijoIzq.cantidadHojas();
        }
        if (hijoDer != null) {
            cantidadHojas += hijoDer.cantidadHojas();
        }
        return cantidadHojas;
    }

    public int cantidadNodosInternos() {
        if (esHoja()) {
            return 0;
        }
        int cantidadNodosInternos = 1;
        if (hijoIzq != null) {
            cantidadNodosInternos += hijoIzq.cantidadNodosInternos();
        }
        if (hijoDer != null) {
            cantidadNodosInternos += hijoDer.cantidadNodosInternos();
        }
        return cantidadNodosInternos;
    }

    public int cantidadNodos() {
        if (esHoja()) {
            return 1;
        }
        int cantidadNodos = 1;
        if (hijoIzq != null) {
            cantidadNodos += hijoIzq.cantidadNodos();
        }
        if (hijoDer != null) {
            cantidadNodos += hijoDer.cantidadNodos();
        }
        return cantidadNodos;
    }

    public int altura() {
        if (esHoja()) {
            return 0;
        }
        int alturaHijoIzquierdo = -1;
        int alturaHijoDerecho = -1;
        if (hijoIzq != null) {
            alturaHijoIzquierdo = hijoIzq.altura();
        }
        if (hijoDer != null) {
            alturaHijoDerecho = hijoDer.altura();
        }
        return Math.max(alturaHijoIzquierdo, alturaHijoDerecho) + 1;
    }

    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda.compareTo(this.dato) == 0) {
            return 0;
        }
        int nivel = -1;
        if (criterioBusqueda.compareTo(this.dato) < 0) {
            if (hijoIzq != null) {
                nivel = hijoIzq.obtenerNivel(criterioBusqueda);
            }
        } else {
            if (hijoDer != null) {
                nivel = hijoDer.obtenerNivel(criterioBusqueda);
            }
        }
        if (nivel != -1) {
            return nivel + 1;
        }
        return -1;
    }
}