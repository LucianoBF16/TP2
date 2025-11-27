package aed;

public class Estudiante implements Comparable<Estudiante>{
    private int id;
    private Heap.Handle referencia;
    private int puntaje;
    private int[] examen;
    private boolean entrego;
    private boolean sospechoso;

    Estudiante(int id, int longitudExamen){ // O(R) 
        this.id = id; // O(1)
        this.examen = new int[longitudExamen]; // O(R)
        for(int i = 0; i < longitudExamen; i++){ // O(R)
            this.examen[i] = -1; // O(1)
        }
        this.puntaje = 0; // O(1) 
        this.entrego = false; // O(1)
        this.sospechoso = false; // O(1)
        this.referencia = null; // O(1)
    }
    //Handle:

    public void actualizarReferencia(Estudiante est){ // O(1)
        this.referencia.actualizarNodo(est); // O(1)
    }

    public void eliminarNodoReferencia(){ // O(1)
        this.referencia.eliminarNodo();
    }

    // Funciones para cambiar variables privadas:
    public void cambiarReferencia(Heap.Handle referencia){ // O(1)
        this.referencia = referencia; // O(1)
    }

    public void cambiarEntrego(boolean entrego){ // O(1)
        this.entrego = entrego; // O(1)
    } 

    public void cambiarExamen(int[] examenNuevo){ // O(1)
        this.examen = examenNuevo; // O(1)
    }

    public void cambiarPosicionExamen(int i, int respuesta){ // O(1)
        this.examen[i] = respuesta; // O(1)
    } 

    public int cambiarPuntaje(int puntaje){ // O(1)
        this.puntaje = puntaje; // O(1) 
        return this.puntaje; // O(1)
    }

    public void cambiarSospechoso(boolean sospechoso){ // O(1)
        this.sospechoso = sospechoso; // O(1)
    }

    // Funciones para obtener variables privadas:

    public int obtenerId(){ // O(1)
        return this.id; // O(1)
    }

    public boolean obtenerSospechoso(){ // O(1)
        return this.sospechoso; // O(1)
    }  

    public Heap.Handle obtenerReferencia(){ // O(1)
        return this.referencia; // O(1)
    }

    public int obtenerPuntaje(){ // O(1)
        return this.puntaje; // O(1)
    }

    public int[] obtenerExamen(){ // O(1)
        return this.examen; // O(1)
    }


    public int compareTo(Estudiante otro) { // O(1)
        int comparar = Integer.compare(this.puntaje, otro.puntaje); // O(1)
        if (comparar != 0) { // O(1)
            return comparar; // O(1)
        }
        return Integer.compare(this.id, otro.id); // O(1)
    }

    @Override
    public String toString() {
        return "id=" + id + "; " + "puntaje=" + puntaje;
    }

    
}
