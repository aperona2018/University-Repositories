package tutorias;



import alumnos.*;
import profesores.Profesor;
import fechas.*;

/**
 * @author aperona2018
 */


/**
 * 
 * Clase InfoTutoria, es una inner class de la clase Tutoría y la usamos para
 * representar la información adicional respecto a la tutoría.
 *
 */

class InfoTutoria {
	private boolean presencial;
	private String asunto;
	
	
	public InfoTutoria(Boolean presencial, String asunto) {
		this.presencial = presencial;
		this.asunto = asunto;
	}
	

	public boolean getPresencial() {
		return presencial;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	
	/**
	 * Convierte en string los atributos de la clase
	 */
	public String toString() {

		return (">>Información de la tutoría: \n" + 
		"Presencialidad --> "+ this.getPresencial() + "\n" +    
		"Asunto --> " + this.getAsunto());
	}
	
	public String infoParse() {
		return (this.getPresencial() + ";" + this.getAsunto());
	}
}	


/**
 * 
 * Clase Tutoria, la usaremos para representar las tutorías que piden los alumnos
 * a los profesores.
 *
 */

public class Tutoria {
	private int numTutoria;
	private Alumno alumno;
	private Profesor profesor;
	private String lugar;
	private InfoTutoria infoTutoria;
	private Fecha fecha;
	
	
	

	public Tutoria(int numTutoria, Alumno alumno, Profesor profesor, 
					Fecha fecha, boolean presencialidad, String asunto) {
		
		this.numTutoria = numTutoria;
		this.alumno = alumno;
		this.profesor = profesor;
		this.lugar = this.profesor.getDespacho();
		this.fecha = fecha;
		this.infoTutoria = new InfoTutoria(presencialidad, asunto);
	}
	
	
	// Getters y setters de la clase
	
	public int getNumTutoria() {
		return numTutoria;
	}


	public String getLugar() {
		return lugar;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public InfoTutoria getInfoTutoria() {
		return infoTutoria;
	}
	
	
	public Alumno getAlumno() {
		return alumno;
	}
	
	public Profesor getProfesor() {
		return profesor;
	}


	/**
	 * Convierte a string toda la información referente a la tutoría
	 * 
	 */
	public String infoToString() {

		return ("\nNumero de tutoria --> " + this.getNumTutoria() + "\n" + 
				"Alumno --> " + this.getAlumno() + "\n" +
				"Profesor --> " + this.getProfesor() + "\n" +
				"Fecha --> " + this.getFecha() + "\n" + this.infoTutoria.toString());
	}
	
	
	/**
	 * Convierte a string partes importantes de la tutoría para poder identificarla.
	 */
	public String toString() {

		return ("Tutoria " + this.getNumTutoria() + " (" + this.getAlumno() + ")");
	}
	
	
	public String infoParse() {
		
		return ("&" + this.getNumTutoria() +  ";" + this.getAlumno().getExpediente() +  ";" + 
				this.getProfesor().getDespacho()  + ";" + this.getFecha() + ";" + 
				this.getInfoTutoria().infoParse());
	}


	public static void main(String[] args) {
		// Casos de uso de prueba para la clase Tutoria
		
		Alumno aTest = new Nuevo("Alumno", "Test", "Ing Telemática", 1);
		Profesor pTest = new Profesor("Profesor", "Test", "AST", "D003");
		Fecha fTest = new Fecha(9, 9, 12, 2022);
		
		Tutoria tutoriaTest = new Tutoria(1, aTest, pTest, fTest, true, "AsuntoTest");
		
		// Prueba de tutToString
		System.out.println("Prueba de infoToString()");
		System.out.println(tutoriaTest.infoToString());
		
		System.out.println("\n\nPrueba de toString()");
		System.out.println(tutoriaTest.toString());
	}

}
