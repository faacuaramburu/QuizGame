package edu.ucu;

public class ColaCircular {
    private int Punteroinicial, Punterofinal, contadorDigitos, tamaño;
    private int [] arrElem;

    public ColaCircular(int tamaño)
    {
        this.tamaño = tamaño;
        this.Punteroinicial = 0;
        this.Punterofinal = -1;
        this.contadorDigitos = 0;
        this.arrElem = new int [tamaño];
    }
    public void agregarACola(int numero)
    {
        if (!this.EstaLleno())
        {
            this.Punterofinal  = (this.Punterofinal +1) % this.tamaño;
            this.arrElem[this.Punterofinal] = numero;
            this.contadorDigitos++;

        } else {
            System.out.println("La cola esta llena, no se pueden agregar mas elementos");
        }
    }
    public int frente()
    {
        if (!this.EstaVacio())
        {
            return this.arrElem[this.Punteroinicial];
        }
        return -1; // Devolver un valor especial para indicar error
    }
    public int quitarDeCola()
    {
        if (!this.EstaVacio())
        {
            int elemento  = this.arrElem[this.Punteroinicial];
            this.Punteroinicial = (this.Punteroinicial +1) % this.tamaño;
            this.contadorDigitos--;
            return elemento;
        } else {
            System.out.println("La cola esta vacia, no se pueden quitar elementos");
            return -1; // Devolver un valor especial para indicar error
        }
    }
    private boolean EstaVacio()
    {
        return (this.contadorDigitos == 0); // TE retorna true si esta vacio
    }
    private boolean EstaLleno()
    {
        return (this.contadorDigitos == this.tamaño); // TE retorna true si esta lleno
    }

}


