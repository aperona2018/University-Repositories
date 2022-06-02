package array;

import java.util.Arrays;
import alumnos.*;

/*
 * Array dinámico de alumnos
 */

/**
 * @author aperona
 *
 */

public class ArrayAlumnos {
	private int numAlumnos = 0;
	private Alumno[] listadoAlumnos; //array de alumnos
	
	
	
	public ArrayAlumnos(int numPosiciones) {
		this.listadoAlumnos = new Alumno[numPosiciones]; 
	}
	
	
	public int size() {
		return this.listadoAlumnos.length;
	}
	
	
	public void add(Alumno a) {
		/*
		 * Método add: Añade un alumno al array
		 * 
		 * ENTRADAS:
		 * alumno a: alumno a añadir
		 * 
		 * SALIDAS:
		 * ninguna, añade el alumno por el final
		 */
		
		this.listadoAlumnos[this.numAlumnos] = a;
		this.numAlumnos++;
			
	}
	
	
	public boolean ins (int posicion, Alumno a) {
		/*
		 * Método ins: Introduce un alumno en una posición dada, si lo logra, 
		 * retorna True, si no se puede, retorna False.
		 * 
		 * ENTRADAS: 
		 * int posicion: posicion en la que queremos introducir el alumno
		 * Alumno a: alumno en cuestion a introducir
		 * 
		 * SALIDAS:
		 * true si se introduce correctamente
		 * false si no se puede introducir
		 */
		if (this.listadoAlumnos[posicion] == null){
			this.listadoAlumnos[posicion] = a;
			return true;
		}
		return false;
	}
	
	
	public Alumno get(int i) {
		/*
		 * Método get: Devuelve un alumno dada su posición. null si no existe
		 * 
		 * ENTRADAS:
		 * int i: posición del array del que queremos sacar un alumno
		 * 
		 * SALIDAS:
		 * el alumno a sacar o null si no hay
		 */
		
		return this.listadoAlumnos[i];

	}
	
	
	public void cambiarTitulaciones(String titulacion) {
		/*
		 * método cambiarTitulaciones: cambia todas las titulaciones de los alumnos
		 * del array por la titulacion pasada en la entrada.
		 * 
		 * ENTRADAS:
		 * String titulacion: titulacion por la que queremos cambiar a todas las
		 * titulaciones de los alumnos del array
		 * 
		 * SALIDAS:
		 * Ninguna, simplemente cambia las titulaciones de los alumnos del array
		 */
		for (Alumno a : this.listadoAlumnos) {
			a.cambiarTitulacion(titulacion);
		}
	}
	
	
	public String titulacionesToString() {
		/*
		 * Método titulacionesToString: retorna un string con las titulaciones de todos los 
		 * alumnos del array. Se usará para demostrar que el método cambiarTitulaciones
		 * funciona.
		 */
		String cadena = "";
		
		for (Alumno a: this.listadoAlumnos) {
			cadena += a.getNombre() + " " + a.getApellidos() + " = " + 
			a.getTitulacion() + "\n";
		}
		
		return cadena;
	}
	
	
	public int getPrecioTotal() {
		/*
		 * Método getPrecioTotal(): calcula el precio total a pagar entre todos los
		 * alumnos del array
		 * 
		 * ENTRADAS:
		 * Ninguna, calcula el precio de los alumnos del array que lo llama
		 * 
		 * SALIDAS:
		 * int precioTotal: precio total a pagar entre todos los alumnos
		 * del array
		 */
		int precioTotal = 0;
		for (Alumno a: this.listadoAlumnos) {
			if (a != null)
				precioTotal += a.getPrecio();
		}
		return precioTotal;
	}
	
	
	public String toString() {
		return Arrays.deepToString(this.listadoAlumnos);
	}
	
	
	public static void main(String[] args) {
		
	}

}
