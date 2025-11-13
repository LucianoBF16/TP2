package aed;

import java.util.logging.Handler;

public class Heap<T extends Comparable<T>>{
    private T[] arrayHeap;
    private Handle[] arrayHandles;
    private int capacidad;


    public class Handle{
        private int posicion;

        Handle(int posicion){ //O(1)
            this.posicion = posicion; //O(1)
        }

        public int posicion(){//O(1)
            return posicion; //O(1)
        }

        public void actualizarNodo(T estudianteActualizado){
            actualizarValor(this.posicion, estudianteActualizado); // log(n)
        }

        public void eliminarNodo(){
            eliminar(this.posicion); //O(log n)
        }

        @Override
        public String toString() {
            return "posicion" + posicion; 
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
            arrayHandles[i] = new Handle(i); // O(1)
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


    public void intercambiar(int padre, int hijo){
        T temporal = arrayHeap[padre]; // O(1)
        arrayHeap[padre] = arrayHeap[hijo]; // O(1)
        arrayHeap[hijo]= temporal; // O(1)

        Handle temporalHandle = arrayHandles[padre]; // O(1)
        arrayHandles[padre] = arrayHandles[hijo]; // O(1)
        arrayHandles[hijo] = temporalHandle; // O(1)
        
        arrayHandles[padre].posicion = padre; // O(1)
        arrayHandles[hijo].posicion = hijo; // O(1)
    }

    public void bajarNodo(int padre){ //O(log(n))
        int menor = padre; // O(1)
        int hijoIzq = (2 * padre) + 1; // O(1) 
        int hijoDer = (2 * padre) + 2; // O(1)

        if (hijoIzq < capacidad && arrayHeap[hijoIzq] != null && 
            arrayHeap[hijoIzq].compareTo(arrayHeap[menor]) < 0) {
            menor = hijoIzq;
        }

        if (hijoDer < capacidad && arrayHeap[hijoDer] != null && 
            arrayHeap[hijoDer].compareTo(arrayHeap[menor]) < 0) {
            menor = hijoDer;
        }

        if (padre != menor){ // O(1)
            intercambiar(padre, menor);
            bajarNodo(menor); // O(log(n))
        }
    }

    public void actualizarValor(int posicion,T Estudiante){
        arrayHeap[posicion] = Estudiante; 
        heapify(posicion);
    }

    public void subirNodo(int hijo){ //O(log(n))
        if (hijo == 0){
            return;
        }
        int padre = (hijo-1)/2; //O(1)
        if (arrayHeap[hijo].compareTo(arrayHeap[padre]) < 0) { // hijo < padre, O(1)
            intercambiar(padre, hijo);
            subirNodo(padre); // O(log(n))
        } 
    }   
    
    public void heapify(int hijo){
        if (hijo < 0 || hijo >= capacidad) {
            return;
        }

        if (hijo == 0) {
            bajarNodo(hijo);
            return;
        }
        
        int padre = (hijo-1)/2; //O(1) 
        if (arrayHeap[hijo].compareTo(arrayHeap[padre]) < 0){
            subirNodo(hijo);
        }
        else{
            bajarNodo(hijo);
        }
    }

    public void eliminar(int pos){
        int posicionFinal = capacidad - 1; // O(1)

        if (pos == posicionFinal) {
            arrayHeap[pos] = null;
            arrayHandles[pos] = null;
            capacidad--;
            return;
        }

        arrayHeap[pos] = arrayHeap[posicionFinal];
        arrayHeap[posicionFinal] = null;

        arrayHandles[pos] = arrayHandles[posicionFinal];
        arrayHandles[pos].posicion = pos;
        arrayHandles[posicionFinal] = null;

        heapify(pos); //O(log n)
        capacidad --; //O(1)
    }

    public T desencolar(){ //O(log(n))
        if (capacidad == 0){//O(1)
            return null; //O(1)
        }

        T raizOriginal = arrayHeap[0];
        
        int posicionFinal = capacidad - 1; //O(1)
        int raiz = 0; //O(1)

        arrayHandles[raiz] = arrayHandles[posicionFinal]; //O(1)
        arrayHandles[raiz].posicion = raiz; //O(1)
        arrayHandles[posicionFinal] = null; //O(1)

        arrayHeap[raiz] = arrayHeap[posicionFinal]; // O(1)
        arrayHeap[posicionFinal] = null;
        capacidad --; //O(1)
        bajarNodo(raiz); //O(log(n))

        return raizOriginal;
    }

    public Handle encolar(T valor){//O(log(n))
        if (capacidad == arrayHeap.length){ //O(1)
            return null;
        }

        int posicionVaciaFinal = capacidad; //O(1)
        arrayHeap[posicionVaciaFinal] = valor;

        Handle nuevoHandle = new Handle(posicionVaciaFinal);
        arrayHandles[posicionVaciaFinal] = nuevoHandle;

        capacidad ++; //O(1)
        subirNodo(posicionVaciaFinal); //O(log(n))

        return nuevoHandle;
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



