package tutorias;

import java.util.Comparator;
import java.util.PriorityQueue;

import alumnos.Alumno;
import alumnos.Nuevo;
import fechas.*;
import profesores.Profesor;


/**
 * @author aperona2018
 */


/**
 * 
 * Clase CollisionException, la creamos debido al reto1upAST para comprobar que no 
 * haya colisiones en las fechas ni en el lugar. Es decir, que no haya dos tutorías que 
 * tengan lugar en el mismo despacho a la vez.
 *
 */

class CollisionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CollisionException () {
		super("Las fechas colisionan con el mismo lugar, no se pudo añadir la tutoria");
	}	
}


/**
 * 
 * Clase ColaTutorias, implementa una PriorityQueue colaTutorias y su pertinente comparador, 
 * en esta clase implementaremos toda la funcionalidad referente al manejo de peticiones de tutorías.
 *
 */

public class ColaTutorias {
	
	private PriorityQueue<Tutoria> colaTutorias;
	private Comparator<Tutoria> comparadorTutorias;
	

	public ColaTutorias() {
		this.comparadorTutorias = new ComparadorTutorias();
		this.colaTutorias = new PriorityQueue<Tutoria>(10, this.comparadorTutorias);
	}
	
	
	public PriorityQueue<Tutoria> getColaTutorias() {
		return colaTutorias;
	}


	/**
	 * Retorna un String con las Tutorías en la cola en orden de prioridad de izquierda a derecha.
	 * 
	 * Nos apoyaremos en una colaAuxiliar para retornar las tutorías a la cola principal una 
	 * vez pintadas puesto que utilizamos remove(). Lo implementamos así debido a que pintar 
	 * la cola directamente no muestra el orden de prioridad de las tutorías que contiene.
	 */
	public String toString() {

		PriorityQueue<Tutoria> colaAux = new PriorityQueue<Tutoria>(10, this.comparadorTutorias);
		String cadena = "[";
		
		while (this.colaTutorias.size() != 0) {
			Tutoria peek = this.colaTutorias.remove();
			cadena += peek;
			if (this.colaTutorias.size() != 0)
				cadena += ", ";
			colaAux.add(peek);
		}
		
		cadena += "]";
		
		// para recuperar colaTutorias a partir de colaAux
		
		while (colaAux.size() != 0) {
			this.colaTutorias.add(colaAux.remove());
		}
		
		return cadena;
	}
	
	
	/**
	 * Comprueba que la tutoría que pasamos de entrada no colisione en fecha y lugar 
	 * con otra tutoría ya en la cola. 
	 * 
	 * @param tut tutoria a comprobar
	 * @return boolean de si colisiona o no
	 */
	public boolean comprobarColision(Tutoria tut) {
		for (Tutoria t: this.colaTutorias) {
			boolean mismaFecha = t.getFecha().equals(tut.getFecha());
			boolean mismoLugar = t.getLugar().equals(tut.getLugar());
			
			if ((mismaFecha)&&(mismoLugar))
				return true;
		}
		return false;
	}
	
	/**
	 * Comprueba que no haya números de tutoría iguales en la tutoría a añadir y las 
	 * tutorías que ya están en la cola
	 * 
	 * @param t tutoria a añadir
	 * @return boolean si se puede añadir o no
	 */
	public boolean comprobarNumTutoria(Tutoria t) {

		for (Tutoria tAux : this.colaTutorias) {
			if (tAux.getNumTutoria() == t.getNumTutoria())
				return false;
		}
		return true;
	}
	
	
	/**
	 * Comprueba que la tutoría a añadir tenga una fecha disponible tanto para su 
	 * alumno como para su profesor.
	 * 
	 * @param t tutoría a añadir
	 * @return boolean si hay disponibilidad mutua o no.
	 */
	public boolean comprobarDisponibilidad(Tutoria t) {
		
		
		boolean dispAlumno = false;
		boolean dispProfe = false;
		
		
		for (Fecha f: t.getAlumno().getFechasDisponibles()) {
			if (f.equals(t.getFecha()))
				dispAlumno = true;
		}
		
		for (Fecha f: t.getProfesor().getFechasDisponibles()) {
			if (f.equals(t.getFecha()))
				dispProfe = true;		
		}
		
		return (dispAlumno && dispProfe);
		
	}
	
	
	/**
	 * Comprueba que no haya colisiones, repeticiones de números de tutoría o indisponibilidades y, 
	 * si se puede, añade la tutoría. 
	 * Además, tras añadirla, elimina la fecha de la tutoría de las fechas disponibles del 
	 * alumno y del profesor.
	 * 
	 * @param t tutoria a añadir
	 */
	public void concertarTutoria (Tutoria t) {
		
		try {
			if (this.comprobarColision(t)) 
				throw new CollisionException();
			
			if (this.comprobarNumTutoria(t) && this.comprobarDisponibilidad(t)) {
				this.colaTutorias.add(t);
				t.getAlumno().eliminarFechaDisponible(t.getFecha());
				t.getProfesor().eliminarFechaDisponible(t.getFecha());
				System.out.println("Tutoría " + t.getNumTutoria() + 
									" concertada con éxito ");
				
			}
			else {
				System.out.println("La tutoría " + t.getNumTutoria() + 
									" no pudo ser concertada");
			}
		} catch (CollisionException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Intenta cancelar la tutoría y si lo hace, vuelve a añadir a las fechas disponibles 
	 * del profesor y del alumno la fecha de la tutoría eliminada
	 * 
	 * @param t tutoría a eliminar
	 */
	public void cancelarTutoria(Tutoria t) {
		
		if (this.colaTutorias.remove(t)) {
			System.out.println("Tutoria " + t.getNumTutoria() + 
							" cancelada con éxito.");
			t.getAlumno().añadirFechaDisponible(t.getFecha());
			t.getProfesor().añadirFechaDisponible(t.getFecha());
		}
		else {
			System.out.println("La Tutoria " + t.getNumTutoria() + 
					" no pudo ser cancelada.");
		}
	}
	
	
	public int size() {
		return this.colaTutorias.size();
	}
	
	
	
    public static void main(String[] args) {
    	
    	// Casos de uso de prueba para la clase ColaTutorias
    	
    	Alumno alumnTest = new Nuevo("Nuevo", "Test", "Telematica", 1);
    	Profesor profTest  = new Profesor("Profe", "Test", "AST", "D123");
    	
    	
    	// Tenemos tres objetos fecha con los mismos atributos
    	Fecha f1 = new Fecha(9, 9, 12, 2018);
    	Fecha f2 = new Fecha(10, 9, 12, 2018);
    	Fecha f3 = new Fecha(11, 9, 12, 2018);
    	
    	
    	
    	alumnTest.añadirFechaDisponible(f1);
    	profTest.añadirFechaDisponible(f1);
    	alumnTest.añadirFechaDisponible(f2);
    	profTest.añadirFechaDisponible(f2);
    	alumnTest.añadirFechaDisponible(f3);
    	
    	// Ahora la disponibilidad será
    	
    	System.out.println("Alumno disp - " + alumnTest.getFechasDisponibles());
    	System.out.println("Profe disp - " + profTest.getFechasDisponibles());
    	
    	// Creamos una tutoria entre profesor y alumno
    	
    	
    	Tutoria tutTest = new Tutoria(1, alumnTest, profTest,f1, false, "Asunto1");
    	
    	Tutoria tutTest2 = new Tutoria(2, alumnTest, profTest,f1, true, "Asunto2");
    	
    	Tutoria tutTest3 = new Tutoria(3, alumnTest, profTest,f3, true, "Asunto3");
    	
    	
    	ColaTutorias colaTest = new ColaTutorias();
    	
    	System.out.println("Concertamos una primera cita a las 9 del 09/12/2018");
    	colaTest.concertarTutoria(tutTest);
    	System.out.println("Concertamos una segunda cita a las 9 del 09/12/2018");
    	colaTest.concertarTutoria(tutTest2);
    	System.out.println("Concertamos una tercera cita a las 11 del 09/12/2018. (No coincide la disponibilidad)"); 
    	colaTest.concertarTutoria(tutTest3); // Las fechas disponibles no coincidiran
    	
    	System.out.println(colaTest);
    	
    	// cancelamos una tutoria y comprobamos que vuelven a estar disponibles en esa fecha
    	colaTest.cancelarTutoria(tutTest);
    	System.out.println(colaTest);
    	
    	System.out.println("Alumno disp - " + alumnTest.getFechasDisponibles());
    	System.out.println("Profe disp - " + profTest.getFechasDisponibles());
    	
    }
}