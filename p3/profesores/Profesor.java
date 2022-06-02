package profesores;


import java.util.ArrayList;
import java.util.Iterator;
import fechas.*;


/**
 * @author aperona2018
*/


/**
 * Clase para representar a un profesor.
 */

public class Profesor {
	
	
	private String nombre;
	private String apellidos;
	private String asignatura;
	private String despacho;
	private ArrayList<Fecha> fechasDisponibles;
	
	
	public Profesor(String nombre, String apellidos, String asignatura, String despacho) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.asignatura = asignatura;
		this.despacho = despacho;
		this.fechasDisponibles = new ArrayList<Fecha>();
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

	
	public String getAsignatura() {
		return asignatura;
	}

	
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	
	public String getDespacho() {
		return despacho;
	}

	
	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}
	
	
	public ArrayList<Fecha> getFechasDisponibles() {
		return fechasDisponibles;
	}
	
	
	public void añadirFechaDisponible(Fecha fecha) {
		this.fechasDisponibles.add(fecha);
	}
	
	
	public boolean eliminarFechaDisponible(Fecha fecha) {
		return this.fechasDisponibles.remove(fecha);
	}
	
	
	public boolean altEliminarFechaDisponible(Fecha fecha) {
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
	 * Convierte a string el nombre y apellidos del profesor
	 * 
	 */
	public String toString() {

		return (this.getNombre() + " " + this.getApellidos());
	}
	
	
	
	/**
	 * Convierte en string los atributos de la clase
	 */
	public String infoToString() {
		
		return (this.toString() + "\n" + "Asignatura: " + this.getAsignatura() + "\n" + 
		"Despacho: " + this.getDespacho() + "\n" + "Fechas disponibles: " +
		this.getFechasDisponibles() + "\n");
	}
	
	

	public static void main(String[] args) {	
		// Casos de uso de prueba para la clase Profesor
		
		Profesor pTest = new Profesor("Profesor", "Test", "AST", "D003");
		Fecha fTest1 = new Fecha(9, 9, 12, 2022);
		Fecha fTest2 = new Fecha(10, 9, 12, 2022);
		pTest.añadirFechaDisponible(fTest1);
		pTest.añadirFechaDisponible(fTest2);
		
		
		// Prueba de toString()
		System.out.println("Prueba de toString()");
		System.out.println(pTest);
		
		// Prueba de infoToString()
		System.out.println("\n\nPrueba de infoToString()");
		System.out.println(pTest.infoToString());
		
	}

}
