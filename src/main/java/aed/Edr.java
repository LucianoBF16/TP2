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
        int cantidadAlumnosPorFila = dimensionAula - (dimensionAula/2); //O(1)
        int vecinoIzquierdo = -1; //O(1)
        int vecinoDerecho = -1; //O(1)
        int vecinoFrente = estudiante - cantidadAlumnosPorFila; //O(1)

        if (estudiante % cantidadAlumnosPorFila == 0){ //O(1)
            vecinoDerecho = estudiante + 1; //O(1)
        }
        else if ((estudiante + 1) % cantidadAlumnosPorFila == 0){ //O(1)
            vecinoIzquierdo = estudiante - 1; //O(1)
        }
        else{ //O(1)
            vecinoDerecho = estudiante + 1; //O(1)
            vecinoIzquierdo = estudiante - 1; //O(1)
        }

        int[] cantidadRespuestasDistintas = new int[3]; //O(1)
           
        if(vecinoIzquierdo >= 0){ //O(1) 
            for(int i = 0; i < estudiantes[estudiante].examen.length; i++){ //O(R)
                if (estudiantes[estudiante].examen[i] != estudiantes[vecinoIzquierdo].examen[i] && estudiantes[vecinoIzquierdo].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[0]++; //O(1)
                }
            }
        }
        
        if(vecinoFrente >= 0){ //O(1)
            for(int i = 0; i < estudiantes[estudiante].examen.length; i++){ //O(R)
                if (estudiantes[estudiante].examen[i] != estudiantes[vecinoFrente].examen[i] && estudiantes[vecinoFrente].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[1]++; //O(1)
                }
            }
        }

        if(vecinoDerecho >= 0 && vecinoDerecho < estudiantes.length){ //O(1)
            for(int i = 0; i < estudiantes[estudiante].examen.length; i++){ //O(R)
                if (estudiantes[estudiante].examen[i] != estudiantes[vecinoDerecho].examen[i] && estudiantes[vecinoDerecho].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[2]++; //O(1)
                }
            }
        }
        
        int maxNoCoincidencias = -1; //O(1)
        int estudianteACopiar = -1; //O(1)
        
        if (cantidadRespuestasDistintas[1] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[1]; //O(1)
            estudianteACopiar = vecinoFrente; //O(1)
        }

        if (cantidadRespuestasDistintas[0] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[0]; //O(1)
            estudianteACopiar = vecinoIzquierdo; //O(1)
        }

        if (cantidadRespuestasDistintas[2] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[2]; //O(1)
            estudianteACopiar = vecinoDerecho; //O(1)
        }

        if (maxNoCoincidencias == 0){ //O(1)
            return; //O(1)
        }
    
        for(int i = 0; i < estudiantes[estudiante].examen.length; i++){ //O(R)
            if(estudiantes[estudiante].examen[i] == -1 && estudiantes[estudianteACopiar].examen[i] != -1){ //O(1)
                estudiantes[estudiante].examen[i] = estudiantes[estudianteACopiar].examen[i]; //O(1)
                if (solucionExamen[i] == estudiantes[estudiante].examen[i]){ //O(1)
                    estudiantes[estudiante].puntaje ++; //O(1)

                    //Actualizar el heap
                    handlesEstudiantesHeap[estudiante].actualizarNodo(estudiantes[estudiante]); //O(log E)
                }
            }
            break; //O(1)
        } 
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
