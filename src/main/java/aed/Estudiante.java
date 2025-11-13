package aed;

import java.util.Arrays;

public class Estudiante implements Comparable<Estudiante>{
    int id;
    Heap.Handle referencia;
    int puntaje;
    int[] examen;

    boolean entrego;
    boolean sospechoso;

    Estudiante(int id, int longitudExamen){
        this.id = id;
        this.examen = new int[longitudExamen];
        for(int i = 0; i < longitudExamen; i++){
            this.examen[i] = -1;
        }
        this.puntaje = 0;
        this.entrego = false;
        this.sospechoso = false;
        referencia = null;
    }

    public int obtenerId(){
        return this.id;
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
        return "id=" + id + "; " + "puntaje=" + puntaje + Arrays.toString(examen) + "handlePos =" + referencia.toString();
    }
}
