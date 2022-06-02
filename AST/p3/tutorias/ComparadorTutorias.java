package tutorias;



import java.util.Comparator;
import alumnos.*;


/**
 * 
 * @author aperona2018
*/


/**
 * Clase comparadora que evalúa la prioridad de los alumnos de cada tutoría y los 
 * organiza en orden de esa prioridad.
 *
 */

public class ComparadorTutorias implements Comparator<Tutoria>
{
	
    @Override
    public int compare(Tutoria t1, Tutoria t2) {
    
    	Alumno a1 = t1.getAlumno();
    	Alumno a2 = t2.getAlumno();
    	
    	if (a1.getPrioridad() < a2.getPrioridad())
    		return 1;
    	else if (a1.getPrioridad() > a2.getPrioridad())
    		return -1;
    	else return 0;
    	
    }
}
