package aed;

import java.util.logging.Handler;

public class Heap<T extends Comparable<T>>{
    private T[] arrayHeap;
    private Handle[] arrayHandles;
    private int capacidad;


    public class Handle{
        private int id;
        private int posicion;

        Handle(int posicion, int id){ //O(1)
            this.posicion = posicion; //O(1)
            this.id = id; //O(1)
        }

        public int posicion(){//O(1)
            return posicion; //O(1)
        }

        public int id(){ //O(1)
            return id; //O(1)
        }

        public void actualizarNodo(){
            
        }
    }

    public Heap(int n){ // O(n)
        this.arrayHeap = (T[]) new Comparable[n]; // O(n)
        this.arrayHandles = new Heap.Handle[n]; // O(n)
        this.capacidad = 0; //O(1)
    }

    public T raiz(){ //O(1)
        return arrayHeap[0]; // O(1)
    }

    /*  
    arrayToHeap:
        -Lo que hace esta funcion es usar el algoritmo de floyd el cual hace lo siguiente:
            - Inicia su iteracion desde el ultimo padre que se encuentra en el penultimo nivel de forma decreciente.
            - Intercambia posiciones con sus hijos respectivos de forma recursiva.
        -Luego devuelve una lista de handles, la cual se va modificando a medida que se intercambia posiciones.
    */

    public Handle[] arrayToHeap(T[] arrayComparable) { 
        arrayHeap = arrayComparable; // O(1)
        int longitudArray = arrayHeap.length; // O(1)
        int ultimoPadre = (longitudArray / 2) - 1; // O(1)
        capacidad = longitudArray;

        for (int i = 0; i < longitudArray; i++){ //O(n)
            arrayHandles[i] = new Handle(i, i); // O(1)
        }
        /*  
            Complejidad total de este ciclo: O(n)
        */

        for (int padre = ultimoPadre; padre >= 0; padre--) { // O((n/2)-1)
            bajarNodo(padre); // O(log n)
        }
        /*  
            Complejidad total de este ciclo: O(n)
            Razon: Algoritmo de Floyd
        */
        
        return arrayHandles; //O(1)
    }
    /*  
        Complejidad total de la funcion: O(n)
    */

    public void bajarNodo(int padre){ //O(log(n))
        int padreActual = padre; // O(1)
        int hijoIzq = (2 * padre) + 1; // O(1) 
        int hijoDer = (2 * padre) + 2; // O(1)

        if (hijoDer < capacidad && arrayHeap[hijoDer].compareTo(arrayHeap[padreActual]) < 0) { // hijoDer < padreActual, O(1)
            padreActual = hijoDer; // O(1)
        } 

        if (hijoIzq < capacidad && arrayHeap[hijoIzq].compareTo(arrayHeap[padreActual]) < 0){ // hijoIzq < padreActual, O(1) 
            padreActual = hijoIzq; // O(1)
        }

        if (padre != padreActual){ // O(1)
            T temporal = arrayHeap[padre]; // O(1)
            arrayHeap[padre] = arrayHeap[padreActual]; // O(1)
            arrayHeap[padreActual]= temporal; // O(1)

            Handle temporalHandle = arrayHandles[padre]; // O(1)
            arrayHandles[padre] = arrayHandles[padreActual]; // O(1)
            arrayHandles[padreActual] = temporalHandle; // O(1)

            arrayHandles[padre].posicion = padre; // O(1)
            arrayHandles[padreActual].posicion = padreActual; // O(1)

            bajarNodo(padreActual); // O(log(n))
        }
    }

    public void subirNodo(int hijo){ //O(log(n))
        int padre = (hijo-1)/2; //O(1)
        if (hijo < capacidad && arrayHeap[hijo].compareTo(arrayHeap[padre]) < 0) { // hijo < padre, O(1)
            T temporal = arrayHeap[padre]; // O(1)
            arrayHeap[padre] = arrayHeap[hijo]; // O(1)
            arrayHeap[hijo]= temporal; // O(1)
            subirNodo(padre); // O(log(n))
        } 
    }

    public void desencolar(){ //O(log(n))
        if (capacidad == 0){//O(1)
            return;
        }
        
        int posicionFinal = capacidad - 1; //O(1)
        int raiz = 0; //O(1)
        arrayHeap[raiz] = arrayHeap[posicionFinal]; // O(1)
        arrayHeap[posicionFinal] = null; // O(1)
        capacidad --; //O(1)
        bajarNodo(raiz); //O(log(n))
    }

    public void encolar(T valor){//O(log(n))
        if (capacidad == arrayHeap.length){ //O(1)
            return;
        }
        
        int posicionVaciaFinal = capacidad; //O(1)
        arrayHeap[posicionVaciaFinal] = valor; //O(1)
        capacidad ++; //O(1)
        subirNodo(posicionVaciaFinal); //O(log(n))
    }

    public void heapify(){
        
    }

    @Override
    public String toString() {//O(n)

        String texto = "["; //O(1)

        for (int i = 0; i < capacidad; i++){//O(n)
            if (i ==capacidad - 1){
                texto = texto + arrayHeap[i].toString(); //O(1)
            }else{
                texto = texto + arrayHeap[i].toString() + ", "; //O(1)
            }
        }

        texto = texto + "]"; //O(1)

        return texto; //O(1)
    }
}



