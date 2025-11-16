package aed;

import java.util.Arrays;

public class Estudiante implements Comparable<Estudiante>{
    private int id;
    private Heap.Handle referencia;
    private int puntaje;
    private int[] examen;
    private boolean entrego;
    private boolean sospechoso;

    Estudiante(int id, int longitudExamen){
        this.id = id;
        this.examen = new int[longitudExamen];
        for(int i = 0; i < longitudExamen; i++){
            this.examen[i] = -1;
        }
        this.puntaje = 0;
        this.entrego = false;
        this.sospechoso = false;
        this.referencia = null;
    }
    //Handle:

    public void actualizarReferencia(Estudiante est){
        this.referencia.actualizarNodo(est);
    }

    public void eliminarNodoReferencia(){
        this.referencia.eliminarNodo();
    }
    // Funciones para cambiar variables privadas:

    public void cambiarReferencia(Heap.Handle referencia){
        this.referencia = referencia;
    }

    public void cambiarEntrego(boolean entrego){
        this.entrego = entrego;
    } 

    public void cambiarExamen(int[] examenNuevo){
        this.examen = examenNuevo;
    }

    public void cambiarPosicionExamen(int i, int respuesta){
        this.examen[i] = respuesta;
    } 

    public int cambiarPuntaje(int puntaje){
        this.puntaje = puntaje;
        return this.puntaje;
    }

    public void cambiarSospechoso(boolean sospechoso){
        this.sospechoso = sospechoso;
    }

    // Funciones para obtener variables privadas:

    public int obtenerId(){
        return this.id;
    }

    public boolean obtenerSospechoso(){
        return this.sospechoso;
    }  

    public Heap.Handle obtenerReferencia(){
        return this.referencia;
    }

    public int obtenerPuntaje(){
        return this.puntaje;
    }

    public int[] obtenerExamen(){
        return this.examen;
    }


    public int compareTo(Estudiante otro) {
        int comparar = Integer.compare(this.puntaje, otro.puntaje);
        if (comparar != 0) {
            return comparar;
        }
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return "id=" + id + "; " + "puntaje=" + puntaje;
    }

    
}
