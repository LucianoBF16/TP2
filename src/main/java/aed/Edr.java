package aed;
import java.util.ArrayList;

public class Edr {
    private Estudiante[] estudiantes;
    private int[] solucionExamen;
    private Heap<Estudiante> estudiantesPorNota;
    private Heap.Handle[] handlesEstudiantesHeap;
    private int dimensionAula;


    public Estudiante estudiante(int id){
        return estudiantes[id];
    }

    /*
     
     */

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {//Esperado: O(E*R)
        //Creamos variables.
        int longitudExamen = ExamenCanonico.length; //O(1)
        
        //Inicializamos arreglo Estudiantes
        this.estudiantes = new Estudiante[Cant_estudiantes];
        for(int i = 0; i < Cant_estudiantes; i++){ //O(E)
            Estudiante nuevoEstudiante = new Estudiante(i, longitudExamen); //O(R)
            estudiantes[i] = nuevoEstudiante; //O(1)
        }   

        //Inicializamos el heap de estudiantes ordenado por entregaron, puntaje y por id.
        Estudiante[] nuevoArrEstudiantes = estudiantes.clone();
        estudiantesPorNota = new Heap<Estudiante>(Cant_estudiantes); //O(E)
        Heap.Handle[] listaHandlesEstudiantes = estudiantesPorNota.arrayToHeap(nuevoArrEstudiantes); //O(E)
        handlesEstudiantesHeap = listaHandlesEstudiantes; //O(1)

        //Asignamos examen canonico a nuestras variables.
        
        solucionExamen = ExamenCanonico; //O(1)

        //Nos guardamos el LadoAula en nuestra variable.

        dimensionAula = LadoAula; //O(1)
        
        //Complejidad total: O(1) + O(E*(R+1)) + O(E+E+1) + O(1) + O(1) = O(E*R)
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){
        double valorEjercicio = 100.0/solucionExamen.length; //O(1)
        double[] notasEstudiante = new double[estudiantes.length];
        for (int i = 0; i < estudiantes.length; i++){ //O(E)
            notasEstudiante[i] = valorEjercicio * estudiantes[i].puntaje; //O(1)
        }
        return notasEstudiante; //O(1)
    }
    //Complejidad total: O(1) + O(E*1) + O(1) = O(E)

//------------------------------------------------COPIARSE------------------------------------------------------------------------


    public void copiarse(int estudiante) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int NroEjercicio, int res) { //Esperado: O(log(E))
        // Actualizamos el examen en estudiantes con la respuesta dada.
        estudiantes[estudiante].examen[NroEjercicio] = res; //O(1)
        // Actualizar puntaje estudiante
        if (solucionExamen[NroEjercicio] == res){
            estudiantes[estudiante].puntaje ++; //O(1)

            //Actualizar el heap
            handlesEstudiantesHeap[estudiante].actualizarNodo(estudiantes[estudiante]);
        }
            
    }



//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int n, int[] examenDW) {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        throw new UnsupportedOperationException("Sin implementar");
    }

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
