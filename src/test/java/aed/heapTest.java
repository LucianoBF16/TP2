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
        int cantidadEstudiantes = 10;
        minHeap = new Heap<>(cantidadEstudiantes);
    
        for (int i = 0; i < cantidadEstudiantes; i++){
            estudiantes[0] = new Estudiante(cantidadEstudiantes);
        }
        
        arrhandles = minHeap.arrayToHeap(estudiantes);
    }


    @Test
    void orderCorrectoHeap() {
        int cantidadEstudiantes = estudiantes.length;
        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantes);

        System.out.println(nuevoHeap.toString());
        
    }

}
