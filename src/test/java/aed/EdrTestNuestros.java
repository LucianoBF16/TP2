package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;


class EdrTestNuestros {
    Edr edr;
    int d_aula;
    int cant_alumnos;
    int[] solucion;

    @BeforeEach
    void setUp(){
        d_aula = 5;
        cant_alumnos = 5;
        solucion = new int[]{0,1,2,3,4,5,6,7,8,9};

        edr = new Edr(d_aula, cant_alumnos, solucion);
    }

    @Test
    void nuevo_edr() {
        double[] notas = edr.notas();
        double[] notas_esperadas = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
    }
    
    @Test
    void los_alumnos_hacen_el_examen() {
        double[] notas;
        double[] notas_esperadas;

        edr.resolver(0, 0, 0);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(2, 0, 2);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        // caso donde los alumnos tienen examen vacio del esudiante que quiere
        edr.copiarse(2);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(1, 5, 4);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 0.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(3, 9, 9);
        notas = edr.notas();
        notas_esperadas = new double[]{10.0, 0.0, 0.0, 10.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(0, 6, 6);
        notas = edr.notas();
        notas_esperadas = new double[]{20.0, 0.0, 0.0, 10.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        // se copia de un ejercicio mal hecho sin agregar punto
        edr.copiarse(2);
        notas = edr.notas();
        notas_esperadas = new double[]{20.0, 0.0, 0.0, 10.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        // se copia del alumno de adelante
        edr.copiarse(3);
        notas = edr.notas();
        notas_esperadas = new double[]{20.0, 0.0, 0.0, 20.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.resolver(1, 0, 0);
        edr.resolver(1, 6, 8);

        edr.copiarse(1);
        notas = edr.notas();
        notas_esperadas = new double[]{20.0, 10.0, 0.0, 20.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.copiarse(2);
        notas = edr.notas();
        notas_esperadas = new double[]{20.0, 10.0, 0.0, 20.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        // alumno entrega examen vacio
        edr.entregar(4);
        edr.resolver(0, 3, 3);

        edr.copiarse(3);
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 10.0, 0.0, 30.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));
        
        int[] darkSolucion = new int[]{0,1,3,2,4,5,6,8,8,9};
        edr.consultarDarkWeb(2,darkSolucion);
        notas = edr.notas();
        notas_esperadas = new double[]{30.0, 70.0, 70.0, 30.0, 0.0};
        assertTrue(Arrays.equals(notas_esperadas, notas));

        edr.entregar(0);
        edr.entregar(1);
        edr.entregar(2);
        edr.entregar(3);
    
        int[] copiones = edr.chequearCopias();

        for(int i = 0; i < copiones.length; i++){
            System.out.println(copiones[i]);
        }
        
        int[] copiones_esperados = new int[]{0,1,2,3};

        assertTrue(Arrays.equals(copiones, copiones_esperados));

        NotaFinal[] notas_finales = edr.corregir();
        NotaFinal[] notas_finales_esperadas = new NotaFinal[]{
            new NotaFinal(0.0, 4),
        };

        assertTrue(Arrays.equals(notas_finales_esperadas, notas_finales));
    }



}

