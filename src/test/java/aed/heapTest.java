package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;

public class heapTest {
    Heap minHeap;
    Estudiante[] estudiantes;

    @BeforeEach
    void setUp(){
        int cantidadEstudiantes = 5;
        minHeap = new Heap<Estudiante>(cantidadEstudiantes);

        estudiantes = new Estudiante[cantidadEstudiantes];
    
        for (int i = 0; i < cantidadEstudiantes; i++){
            estudiantes[i] = new Estudiante(i,0);
        }
        
        Heap.Handle[] arrhandles = minHeap.arrayToHeap(estudiantes.clone());

        for (int i = 0; i < arrhandles.length; i++){
            estudiantes[i].cambiarReferencia(arrhandles[i]);
        }
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

        estudiantesAlReves[4].cambiarPuntaje(1);
        estudiantesAlReves[2].cambiarPuntaje(1);
        estudiantesAlReves[3].cambiarPuntaje(4);
        estudiantesAlReves[1].cambiarPuntaje(7);
        estudiantesAlReves[0].cambiarPuntaje(5);

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

        estudiantesAlReves[4].cambiarPuntaje(2);
        estudiantesAlReves[2].cambiarPuntaje(2);
        estudiantesAlReves[3].cambiarPuntaje(3);
        estudiantesAlReves[1].cambiarPuntaje(1);
        estudiantesAlReves[0].cambiarPuntaje(5);

        // Orden y valores de los estudiantes previos al heap: [id=4; puntaje=5, id=3; puntaje=1, id=2; puntaje=2, id=1; puntaje=3, id=0; puntaje=2]
        
        Heap<Estudiante> nuevoHeap = new Heap(cantidadEstudiantes);
        nuevoHeap.arrayToHeap(estudiantesAlReves);
        assertEquals("[id=3; puntaje=1, id=0; puntaje=2, id=2; puntaje=2, id=1; puntaje=3, id=4; puntaje=5]", nuevoHeap.toString());

        nuevoHeap.desencolar();
        nuevoHeap.desencolar();
        assertEquals("[id=2; puntaje=2, id=1; puntaje=3, id=4; puntaje=5]", nuevoHeap.toString());

        Estudiante nuevoEstudiante = new Estudiante(3,0);
        Estudiante nuevoEstudiante1 = new Estudiante(0,0);
        nuevoEstudiante1.cambiarPuntaje(4);
        nuevoHeap.encolar(nuevoEstudiante);
        nuevoHeap.encolar(nuevoEstudiante1);

        assertEquals("[id=3; puntaje=0, id=2; puntaje=2, id=4; puntaje=5, id=1; puntaje=3, id=0; puntaje=4]", nuevoHeap.toString());
    } 

    @Test
    void iniciarHeapEncolandoElementoYAlLimite() {
        Heap<Estudiante> nuevoHeap = new Heap<>(3);

        Estudiante estudiantes1 = new Estudiante(1,0);
        Estudiante estudiantes2 = new Estudiante(2,0);
        Estudiante estudiantes3 = new Estudiante(0,0);

        nuevoHeap.encolar(estudiantes1);
        nuevoHeap.encolar(estudiantes2);
        nuevoHeap.desencolar();
        nuevoHeap.encolar(estudiantes3);

        assertEquals("[id=0; puntaje=0, id=2; puntaje=0]", nuevoHeap.toString());

        nuevoHeap.desencolar();
        nuevoHeap.desencolar();


        assertEquals("[]", nuevoHeap.toString());

    } 

    @Test
    void eliminarConHandleFuncionaCorrectamente() {
        estudiantes[0].cambiarPuntaje(2);
        estudiantes[0].actualizarReferencia(estudiantes[0]);
        
        estudiantes[1].cambiarPuntaje(1);
        estudiantes[1].actualizarReferencia(estudiantes[1]);
        
        estudiantes[2].cambiarPuntaje(4);
        estudiantes[2].actualizarReferencia(estudiantes[2]);

        System.out.println(minHeap.toString());

        assertEquals("[id=3; puntaje=0, id=4; puntaje=0, id=1; puntaje=1, id=0; puntaje=2, id=2; puntaje=4]", minHeap.toString());

        estudiantes[1].eliminarNodoReferencia();

        System.out.println(minHeap.toString());
        assertEquals("[id=3; puntaje=0, id=4; puntaje=0, id=2; puntaje=4, id=0; puntaje=2]", minHeap.toString());
    }

    @Test
    void encolarCreaHandleValido() {
        Heap<Estudiante> nuevoHeap = new Heap<>(2);

        Estudiante est1 = new Estudiante(10, 0);
        Estudiante est2 = new Estudiante(11, 0);

        est1.cambiarPuntaje(3);
        est2.cambiarPuntaje(1);

        // Encolar y verificar que se crean handles
        Heap.Handle handle1 = nuevoHeap.encolar(est1);
        est1.cambiarReferencia(handle1);

        Heap.Handle handle2 = nuevoHeap.encolar(est2);
        est2.cambiarReferencia(handle2);

        // Verificar que los handles fueron asignados
        assertNotNull(est1.obtenerReferencia());
        assertNotNull(est2.obtenerReferencia());

        // Verificar el orden correcto del heap
        assertEquals("[id=11; puntaje=1, id=10; puntaje=3]", nuevoHeap.toString());
    }
    
}
