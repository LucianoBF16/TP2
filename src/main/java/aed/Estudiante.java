package aed;

public class Estudiante implements Comparable<Estudiante>{
    int id;
    int referencia;
    int puntaje;
    int[] examen;

    boolean entrego;
    boolean sospechoso;

    Estudiante(int id, int longitudExamen){
        this.id = id;
        this.examen = new int[longitudExamen];
        this.puntaje = 0;
        this.entrego = false;
        this.sospechoso = false;
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
