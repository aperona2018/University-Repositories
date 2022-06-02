package alumnos;


import java.util.*;

import fechas.Fecha;


/**
 * @author aperona2018
*/

/**
 * Clase padre de Erasmus, Nuevo y Repetidor, aquí implementaremos la mayor
 * parte de las funcionalidades de los alumnos
 */
public class Alumno {
	
	private String nombre;
	private String apellidos;
	protected int precio;  //La definimos como protected para acceder sin setter
	private String titulacion;
	private int expediente;
	protected int prioridad;
	private ArrayList<Fecha> fechasDisponibles;
	
	
	private final int PRECIO_ERASMUS = 80;
	private final int PRECIO_NUEVO = 60;
	private final int PRECIO_REPETIDOR = 70;
	
	
	public Alumno(String nombre, String apellidos, String titulacion, int expediente) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.titulacion = titulacion;
		this.expediente = expediente;
		this.precio = 0;
		this.prioridad = 0;
		this.fechasDisponibles = new ArrayList<Fecha>();
	}

	
	public int getPrecio() {
		return precio;
	}
	
	
	public int getExpediente() {
		return expediente;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getApellidos() {
		return apellidos;
	}

	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	public String getTitulacion() {
		return titulacion;
	}


	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	
	
	public int getPrioridad() {
		return prioridad;
	}


	public ArrayList<Fecha> getFechasDisponibles() {
		return fechasDisponibles;
	}

	
	public int getPRECIO_ERASMUS() {
		return PRECIO_ERASMUS;
	}


	public int getPRECIO_NUEVO() {
		return PRECIO_NUEVO;
	}


	public int getPRECIO_REPETIDOR() {
		return PRECIO_REPETIDOR;
	}
	
	
	public void añadirFechaDisponible(Fecha fecha) {
		this.fechasDisponibles.add(fecha);
	}
	
	
	public boolean eliminarFechaDisponible(Fecha fecha) {
		return this.fechasDisponibles.remove(fecha);
	}
	
	
	/**
	 * Este método lo creamos para eliminar objetos Fecha distintos pero que
	 * contienen la misma fecha dentro de la lista de fechas disponibles, 
	 * es decir, objetos Fecha distintos que simbolizan 
	 * la misma fecha. No obstante, el profesor nos ha indicado que solo puede
	 * haber un solo objeto Fecha para cada fecha. Por lo que no lo usaremos.
	 * Nos apoyamos en un iterador para comparar fechas.
	 * 
	 *  
	 *  @param fecha la fecha a eliminar
	 *  @return boolean si se ha eliminado o no
	 */
	public boolean altEliminarFechaDisponible(Fecha fecha) {
		
		// Creamos un iterador auxiliar
		Iterator<Fecha> it = this.fechasDisponibles.iterator();
		while (it.hasNext()) {
			Fecha f = it.next();
			if (f.equals(fecha)) {
				this.fechasDisponibles.remove(f);
				return true;
			}	
		}
		return false;
	}
	
	
	/**
	 * Devuelve el precio de un tipo de alumno dado un int tipoalumno
	 * 
	 * 
	 * @param tipoalumno 0 para Nuevo, 1 para Repetidor y 2 para Erasmus
	 * @return precio del tipo del alumno
	 * 
	 * OJO: No usaremos este método, ya que el getPrecio() de cada instancia creada 
	 * nos resuelve el problema de una manera mas elegante.
	 *
	 */
	public int precio(int tipoalumno) {
		

		int precio = 0;

		switch (tipoalumno) {
			case 0:
				precio = this.getPRECIO_NUEVO();
				break;
			case 1:
				precio = this.getPRECIO_REPETIDOR();
				break;
			case 2:
				precio = this.getPRECIO_ERASMUS();
				break;
			default:
				return 0;
		}
		
		return precio;
	}
	
	
	/**
	 * Convierte en string los atributos de la clase
	 * 
	 * ENTRADAS: Ninguna, retorna un string de los atributos de quien lo llama
	 * 
	 * @return Un string con los atributos de quien lo llama
	 */
	public String infoToString() {
		
		return (this.getNombre() + " " + this.getApellidos() + "\n" + "Titulacion: " + this.getTitulacion() + "\n" + 
		"Expediente: " + this.getExpediente() + "\n" + "Precio matricula: " + this.getPrecio() + " euros." + "\n" +
		"Fechas disponibles: " + this.getFechasDisponibles() + "\n");
		
	}
	
	
	/**
	 * Cambia levemente respecto del infoToString, convierte 
	 * en string el nombre y los apellidos de quien lo llama
	 */
	public String toString() {

		return (this.getNombre() + " " + this.getApellidos());
	}
	
	
	/**
	 * Cambia la titulación del alumno que lo llama
	 * 
	 * @param titulacion por la que cambiamos la titulación
	 * del alumno
	 * 
	 * SALIDAS:
	 * Ninguna, cambia la titulación del alumno.
	 */
	public void cambiarTitulacion(String titulacion) {
		
		this.setTitulacion(titulacion);
		
	}


	public static void main(String[] args) {
		// Casos de uso de prueba para la clase Alumno
		// OJO: Esto lo hacemos para probar ciertos métodos, cuando queramos
		// crear realmente un alumno, lo haremos como Nuevo, Repetidor o Erasmus
		
		Alumno aTest = new Nuevo("Alumno", "Test", "Ing Telematica", 1);
		Fecha fTest1 = new Fecha(9, 9, 12, 2022);
		Fecha fTest2 = new Fecha(10, 9, 12, 2022);
		aTest.añadirFechaDisponible(fTest1);
		aTest.añadirFechaDisponible(fTest2);
				
				
		// Prueba de toString()
		System.out.println("Prueba de toString()");
		System.out.println(aTest);
				
		// Prueba de infoToString()
		System.out.println("\n\nPrueba de infoToString()");
		System.out.println(aTest.infoToString());
		
		
		// Prueba de eliminarFechaDisponible
		
		aTest.eliminarFechaDisponible(fTest2);
		
		System.out.println(aTest.getFechasDisponibles());
	}

}
