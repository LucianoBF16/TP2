package aed;
import java.util.ArrayList;
import java.util.Arrays;

public class Edr {
    private Estudiante[] estudiantes;
    private int[] solucionExamen;
    private Heap<Estudiante> estudiantesPorNota;
    private int dimensionAula;


    //Para hacer toString:
    public Estudiante estudiante(int id){
        return estudiantes[id];
    }

    /*
     
     */

    public Edr(int LadoAula, int Cant_estudiantes, int[] ExamenCanonico) {//Esperado: O(E * R)
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

        //Unificamos handle con estudiantes

        for(int i = 0; i < Cant_estudiantes; i++){ //O(E)
            estudiantes[i].referencia = listaHandlesEstudiantes[i]; //O(1) 
        }   

        //Asignamos examen canonico a nuestras variables.
        
        solucionExamen = ExamenCanonico; //O(1)

        //Nos guardamos el LadoAula en nuestra variable.

        dimensionAula = LadoAula; //O(1)
        
        //Complejidad total: O(1) + O(E*(R+1)) + O(E+E+1) + O(1) + O(1) = O(E * R)
    }

//-------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas(){//esperado: O(E)
        //Inicializamos Variables        
        double valorEjercicio = 100.0/solucionExamen.length; //O(1)
        double[] notasEstudiante = new double[estudiantes.length]; // O(1)

        //Insertamos en notasEstudiantes por menor id la nota que tiene respectiva a su puntaje
        for (int i = 0; i < estudiantes.length; i++){ //O(E)
            notasEstudiante[i] = valorEjercicio * estudiantes[i].puntaje; //O(1)
        }

        //Devolvemos las notas
        return notasEstudiante; //O(1)
    }
    //Complejidad total: O(1) + O(E*1) + O(1) = O(E)

//------------------------------------------------COPIARSE------------------------------------------------------------------------


    public void copiarse(int estudiante) {//esperado: O(R+log E)

        //Inicializamos variables, segun el id del estudiante actual calculamos los posibles id de los estudiantes cercanos, siempre y cuando se pueda su valor 
        //sera >= 0 
        int cantidadAlumnosPorFila = dimensionAula; //O(1)
        int vecinoIzquierdo = (estudiante % cantidadAlumnosPorFila == 0) ? -1 : estudiante - 1; //O(1)
        int vecinoDerecho = (((estudiante + 1) % cantidadAlumnosPorFila == 0)) ? -1 : estudiante + 1; //O(1)
        int vecinoFrente = estudiante - cantidadAlumnosPorFila; //O(1)

        
        //Creamos un array donde cada posicion hace alusion a:
        //2 = vecinoDerecho
        //1 = vecinoFrente
        //0 = vecinoIzquierdo
        int[] cantidadRespuestasDistintas = new int[3]; //O(1)
        

        // Iteramos en el examen del estudiante comparando con los vecinos si existen y sumamos la cantidad de respuestas distintas de cada uno.
        
        for(int i = 0; i < estudiantes[estudiante].examen.length; i++){ //O(R)
        
            if(vecinoDerecho >= 0 && vecinoDerecho < estudiantes.length){ //O(1)
                if (estudiantes[estudiante].examen[i] == -1 && estudiantes[vecinoDerecho].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[2]++; //O(1)
                }
            }

            if(vecinoIzquierdo >= 0){ //O(1) 
                if (estudiantes[estudiante].examen[i] == -1 && estudiantes[vecinoIzquierdo].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[1]++; //O(1)
                }
            }

            if(vecinoFrente >= 0){ //O(1)
                if (estudiantes[estudiante].examen[i] == -1 && estudiantes[vecinoFrente].examen[i] != -1){ //O(1)
                    cantidadRespuestasDistintas[0]++; //O(1)
                }
            }
        }

        //Buscamos aquel vecino con mayor cantidad de respuestas disintas a las mias sino mayor id 

        int maxNoCoincidencias = -1; //O(1)
        int estudianteACopiar = -1; //O(1)

        if (cantidadRespuestasDistintas[2] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[2]; //O(1)
            estudianteACopiar = vecinoDerecho; //O(1)
        }

        if (cantidadRespuestasDistintas[1] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[1]; //O(1)
            estudianteACopiar = vecinoIzquierdo; //O(1)
        }
        
        if (cantidadRespuestasDistintas[0] > maxNoCoincidencias) { //O(1)
            maxNoCoincidencias = cantidadRespuestasDistintas[0]; //O(1)
            estudianteACopiar = vecinoFrente; //O(1)
        }   

        // Si nadie tiene respuestas distintas no se copia.

        if (maxNoCoincidencias == 0){ //O(1)
            return; //O(1)
        }
        
        // Copiamos el primer ejercicio, sumamos puntaje y actualizamos nodo en el heap.
        
        boolean noSeCopio = true; //O(1)
        int i = 0; //O(1)
        while(noSeCopio && i < estudiantes[estudiante].examen.length){ //O(R)
            if(estudiantes[estudiante].examen[i] == -1 && estudiantes[estudianteACopiar].examen[i] != -1){ //O(1)
                estudiantes[estudiante].examen[i] = estudiantes[estudianteACopiar].examen[i]; //O(1)
                if (solucionExamen[i] == estudiantes[estudiante].examen[i]){ //O(1)
                    estudiantes[estudiante].puntaje ++; //O(1)

                    //Actualizar el heap
                    estudiantes[estudiante].referencia.actualizarNodo(estudiantes[estudiante]); //O(log E)
                }
                noSeCopio = false; //O(1)
            }
            i++; //O(1)
        } 
    }  
    
    //Complejidad total: O(R) + O(R * log E)= O(R * log E)
    
//-----------------------------------------------RESOLVER----------------------------------------------------------------




    public void resolver(int estudiante, int NroEjercicio, int res) { //Esperado: O(log(E))
        // Actualizamos el examen en estudiantes con la respuesta dada.
        estudiantes[estudiante].examen[NroEjercicio] = res; //O(1)

        // Actualizar puntaje estudiante
        if (solucionExamen[NroEjercicio] == res){ //O(1)
            estudiantes[estudiante].puntaje ++; //O(1)
        }

        //Actualizar el heap
        estudiantes[estudiante].referencia.actualizarNodo(estudiantes[estudiante]); //O(log E)
            
    }

    //Complejidad total: O(1) + O(1) + O(log E) = O(log E)

//------------------------------------------------CONSULTAR DARK WEB-------------------------------------------------------

    public void consultarDarkWeb(int n, int[] examenDW) {

        // Calculamos el puntaje que obtenemos del examenDW, para insertarlo e
        int puntajeExamenDw = 0; //O(1)
        for(int j = 0; j < examenDW.length; j++){ //O(R)
            if(examenDW[j] == solucionExamen[j]){ //O(1)
                puntajeExamenDw++; //O(1)
            }
        }

        // Crear una lista temporal para los n estudiantes con menores puntajes
        Estudiante[] estudiantesMenores = new Estudiante[n]; //O(n) 
        for(int i = 0; i < n; i++){ //O(n) 
            estudiantesMenores[i] = estudiantesPorNota.desencolar(); //O(log E) 
        }

        // Actualizar los n estudiantes y los encola en el heap
        for(int i = 0; i < n; i++){ //O(n)
            Estudiante est = estudiantesMenores[i]; //O(1)
            est.examen = examenDW.clone(); //O(R)
            est.puntaje = puntajeExamenDw; //O(1)
            est.referencia = estudiantesPorNota.encolar(est); //O(log E)
        }
    }

    //Complejidad total: O(1) + O(R) + O(n) + O(n * log E) + O(n * (R + log E)) = O(n * (R + log E))

//-------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) { //esperado: O(log E)

        //Cambiamos valores internos del estudiante
        estudiantes[estudiante].entrego = true; //O(1)

        //Lo eliminamos del heap
        estudiantes[estudiante].referencia.eliminarNodo(); //O(log E)
        
    }

    //Complejidad total: O(1) + O(log E) = O(log E)

//-----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() { //esperado: O(E * log E)

        //Calculamos la cantidad de estudiantes que no se copiaron.
        int estudiantesNoCopiados = 0; //O(1)

        for(int i = 0; i<estudiantes.length;i++){ //O(E)
            if (estudiantes[i].sospechoso == false){ //O(1)
                estudiantesNoCopiados ++; //O(1)
            } 
        }


        //Inicializamos un heap local y un array de estudiantes que no se hayan copiado.
        Heap<NotaFinal> heapNotaFinal = new Heap<>(estudiantesNoCopiados);  //O(log E)
        NotaFinal[] estudiantesOrdenados = new NotaFinal[estudiantesNoCopiados]; //O(E)

        //Calculamos la nota de cada estudiante
        double[] notaPorEstudiante = notas(); //O(E)

        // Creo para cada estudiante un NotaFinal y lo introduzco en el heap.
        for(int i = 0; i< estudiantes.length;i++){ //O(E)
            if (estudiantes[i].sospechoso == false){ //O(1)
                NotaFinal estudianteFinal = new NotaFinal(notaPorEstudiante[i], i); //O(1)
                heapNotaFinal.encolar(estudianteFinal); //O(log E)
            }
        }

        //Desencolo todos los estudiantes de forma ordenada
        for(int i = 0; i < estudiantesNoCopiados;i++){ //O(E) 
            NotaFinal maximo = heapNotaFinal.raiz(); //O(1)
            heapNotaFinal.desencolar(); //O(log E) 
            estudiantesOrdenados[i] = maximo; //O(1)
        }


        return estudiantesOrdenados; //O(1)
    }

    //Complejidad total: O(1) + O(E) + O(log E) + O(E) + O(E) + O(E * log E) + O(E * log E) = O(E * log E)

//-------------------------------------------------------CHEQUEAR COPIAS-------------------------------------------------

    public int[] chequearCopias() { //esperado: O(E * R)
        //Inicializamos un array de longitud R donde cada posicion tiene a su vez un array de longitud acotada 10 que hace alusion a la cantidad de personas
        //que eligen esa respuesta, ejemplo:
        //Examen 4 ejercicios con respuestas multiple choice del 0 al 4
        // int[[0,1,0,0,0],[0,0,3,0,0],[0,0,4,0,0],[0,0,0,0,0]]

        int[] estudiantesCopiados; // O(E)
        int[][] cuentaEjercicios = new int[solucionExamen.length][10]; //O(R*10)
        

        
        //Sumamos todas las repuestas de los estudiantes
        for(int est = 0; est < estudiantes.length; est++){ // O(E)
            for(int ej = 0; ej < solucionExamen.length; ej++){ //O(R)
                int respuesta = estudiantes[est].examen[ej]; // O(1)
                if(estudiantes[est].examen[ej] != -1){ //O(1)
                    cuentaEjercicios[ej][respuesta] ++; //O(1)
                }
            }
        }

        //Pasamos por cada estudiante y verificamos si se copiaron o no con el criterio del 25%.
        for(int est = 0; est < estudiantes.length; est++){ // O(E)
            boolean estudianteSeCopio = true; //O(1)
            int respondidas=0; //O(1)
            int ej = 0; //O(1)
            
            while(ej < solucionExamen.length ){ //O(R)
                int respuesta= estudiantes[est].examen[ej]; //O(1)
                if(estudiantes[est].examen[ej] != -1){ //O(1)
                    respondidas ++; //O(1)
                    if ((cuentaEjercicios[ej][respuesta] - 1) < (estudiantes.length - 1) * 0.25){ //O(1)
                        estudianteSeCopio = false; //O(1)
                        break; //O(1)
                    }
                }

                ej++; //O(1)
            }

            if (respondidas==0){ //O(1)
                estudianteSeCopio = false; //O(1)
            }

            estudiantes[est].sospechoso = estudianteSeCopio; //O(1)
        }

        //Calculamos la cantidad de estudiantes que se copiaron
        int cantidadEstudiantesCopiados = 0; //O(1)

        for(int est = 0; est < estudiantes.length; est++){ // O(E)
            if(estudiantes[est].sospechoso == true){ //O(1)
                cantidadEstudiantesCopiados ++; //O(1)
            }
        }

        //Iniciamos un array con la cantidad de estudiantes que se copiaron

        estudiantesCopiados = new int[cantidadEstudiantesCopiados]; //O(E) 

        //Si es sospechoso entonces lo metemos en el array
        int j = 0; //O(1)
        for(int est = 0; est < estudiantes.length; est++){ // O(E)
            if(estudiantes[est].sospechoso == true){ //O(1)
                estudiantesCopiados[j] = estudiantes[est].id; //O(1)
                j ++; //O(1)
            }
        }

        return estudiantesCopiados; //O(1)
    }

    //Complejidad total: O(E) + O(E * R) + O(E * R) + O(E) + O(E) + O(E) = O(E * R)
}
