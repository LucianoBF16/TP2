package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;

public class heapTest {
    Heap minHeap;
    Estudiante[] estudiantes;
    Heap.Handle[] arrhandles;

    @BeforeEach
    void setUp(){
        int cantidadEstudiantes = 5;
        minHeap = new Heap<Estudiante>(cantidadEstudiantes);

        estudiantes = new Estudiante[cantidadEstudiantes];
    
        for (int i = 0; i < cantidadEstudiantes; i++){
            estudiantes[i] = new Estudiante(i,0);
        }
        
        arrhandles = minHeap.arrayToHeap(estudiantes);
    }

    @Test
    void ArrayToHeapVacio() {
    }


    @Test
    void ordenCorrectoHeapPorId() {
        int cantidadEstudiantes = estudiantes.length;
        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantes);
        System.out.println(nuevoHeap.toString());
    }

    @Test
    void ordenCorrectoEstudiantesAlRevesPorId() {
        int cantidadEstudiantes = estudiantes.length;
        Estudiante[] estudiantesAlReves = new Estudiante[cantidadEstudiantes];
        
        for(int i = 0; i < estudiantes.length; i++){
            estudiantesAlReves[(estudiantes.length-1)-i] = estudiantes[i];
        }

        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantesAlReves);
    }

    @Test
    void ordenCorrectoEstudiantesAlRevesPorPuntajeYId() {
        int cantidadEstudiantes = estudiantes.length;
        Estudiante[] estudiantesAlReves = new Estudiante[cantidadEstudiantes];
        
        for(int i = 0; i < estudiantes.length; i++){
            estudiantesAlReves[(estudiantes.length-1)-i] = estudiantes[i];
        }

        estudiantesAlReves[4].puntaje = 1;
        estudiantesAlReves[2].puntaje = 1;
        estudiantesAlReves[3].puntaje = 4;
        estudiantesAlReves[1].puntaje = 7;
        estudiantesAlReves[0].puntaje = 5;

        // Orden y valores de los estudiantes previos al heap: [id=4; puntaje=5, id=3; puntaje=7, id=2; puntaje=1, id=1; puntaje=4, id=0; puntaje=1]

        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantesAlReves);
        assertEquals("[id=0; puntaje=1, id=1; puntaje=4, id=2; puntaje=1, id=4; puntaje=5, id=3; puntaje=7]", nuevoHeap.toString());
    }   

    @Test
    void desencolarEncolarAlRevesOrdenadoPuntajeId() {
        int cantidadEstudiantes = estudiantes.length;
        Estudiante[] estudiantesAlReves = new Estudiante[cantidadEstudiantes];
        
        for(int i = 0; i < estudiantes.length; i++){
            estudiantesAlReves[(estudiantes.length-1)-i] = estudiantes[i];
        }

        estudiantesAlReves[4].puntaje = 2;
        estudiantesAlReves[2].puntaje = 2;
        estudiantesAlReves[3].puntaje = 3;
        estudiantesAlReves[1].puntaje = 1;
        estudiantesAlReves[0].puntaje = 5;

        // Orden y valores de los estudiantes previos al heap: [id=4; puntaje=5, id=3; puntaje=1, id=2; puntaje=2, id=1; puntaje=3, id=0; puntaje=2]
        
        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantesAlReves);
        assertEquals("[id=3; puntaje=1, id=0; puntaje=2, id=2; puntaje=2, id=1; puntaje=3, id=4; puntaje=5]", nuevoHeap.toString());

        nuevoHeap.desencolar();
        nuevoHeap.desencolar();
        assertEquals("[id=2; puntaje=2, id=1; puntaje=3, id=4; puntaje=5]", nuevoHeap.toString());

        Estudiante nuevoEstudiante = new Estudiante(3,0);
        Estudiante nuevoEstudiante1 = new Estudiante(0,0);
        nuevoEstudiante1.puntaje = 4;
        nuevoHeap.encolar(nuevoEstudiante);
        nuevoHeap.encolar(nuevoEstudiante1);

        assertEquals("[id=3; puntaje=0, id=2; puntaje=2, id=4; puntaje=5, id=1; puntaje=3, id=0; puntaje=4]", nuevoHeap.toString());
    } 

    @Test
    void iniciarHeapEncolandoElemento() {
        
    } 

    @Test
    void arrayDeHandlesValido() {
        
    } 
    
    

}
