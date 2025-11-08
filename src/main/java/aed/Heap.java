package aed;

import java.util.logging.Handler;

public class Heap<T extends Comparable<T>>{
    private T[] arrayHeap;
    private Handle[] arrayHandles;


    public class Handle{
        private int id;
        private int posicion;

        public Handle(int posicion, int id){
            this.posicion = posicion;
            this.id = id;
        }

        public int posicion(){
            return posicion;
        }

        public int id(){
            return id;
        }

        public void heapify(){

        }
    }

    public Heap(int n){ //O(n)
        this.arrayHeap = (T[]) new Object[n]; //O(n)
        this.arrayHandles =  new Heap.Handle[n];
    }

    public T raiz(){ //O(1)
        return arrayHeap[0]; // O(1)
    }

    /*  
    Lo que hace esta funcion es usar el algoritmo de floyd el cual hace lo siguiente:
        - Inicia su iteracion desde el ultimo padre que se encuentra en el penultimo nivel de forma decreciente.
        - Intercambia posiciones con sus hijos respectivos de forma recursiva.
    Luego devuelve una lista de handles, la cual se va modificando a medida que se intercambia posiciones.
    */

    public Handle[] arrayToHeap(T[] arrayComparable) {
        arrayHeap = arrayComparable; // O(1)
        int longitudArray = arrayHeap.length; // O(1)
        int ultimoPadre = (longitudArray / 2) - 1; // O(1)

        for (int i = 0; i < longitudArray; i++){ //O(n)
            arrayHandles[i] = new Handle(i, i); // O(1)
        }
        /*  
            Complejidad total de este ciclo: O(n)
        */

        for (int padre = ultimoPadre; padre >= 0; padre--) { // O((n/2)-1)
            intercambiar(padre); // O(log n)
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

    public void intercambiar(int padre){
        int longitudArray = arrayHeap.length; // O(1)
        int padreActual = padre; // O(1)
        int hijoIzq = (2 * padre) + 1; // O(1) 
        int hijoDer = (2 * padre) + 2; // O(1)

        if (hijoDer < longitudArray && arrayHeap[hijoDer].compareTo(arrayHeap[padreActual]) < 0) { // O(1)
            padreActual = hijoDer; // O(1)
        }

        if (hijoIzq < longitudArray && arrayHeap[hijoIzq].compareTo(arrayHeap[padreActual]) < 0){ // O(1) 
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

            intercambiar(padreActual); // O(log(n))
        }
    }

    public void heapify(){
        
    }


    @Override
    public String toString() {

        String texto = "[";

        for (int i = 0; i < arrayHeap.length; i++){
            if (i ==arrayHeap.length - 1){
                texto = texto + arrayHeap.toString();
            }else{
                texto = texto + arrayHeap.toString() + ",";
            }
        }

        texto = texto + "]";

        return texto;
    }
}



