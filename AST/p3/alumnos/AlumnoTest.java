package alumnos;

import static org.junit.Assert.*;
import org.junit.Test;
import fechas.Fecha;

public class AlumnoTest {

	
	@Test
	public void añadirFecha() {
		Alumno alumno = new Nuevo("Alumno", "Test", "titulacionTest", 0);
		Fecha fecha = new Fecha(9,23,3,2022);
		
		System.out.println("Añadiendo fecha disponible...");
		alumno.añadirFechaDisponible(fecha);
		assertTrue(alumno.getFechasDisponibles().size() == 1);
		
	}
	
	
	@Test
	public void eliminarFecha() {
		Alumno alumno = new Nuevo("Alumno", "Test", "titulacionTest", 0);
		Fecha fecha = new Fecha(9,23,3,2022);
		alumno.añadirFechaDisponible(fecha);
		
		System.out.println("Eliminando fecha disponible...");
		alumno.eliminarFechaDisponible(fecha);
		assertTrue(alumno.getFechasDisponibles().size() == 0);
	}
	
	
	@Test
	public void cambioTitulacion() {
		Alumno alumno = new Nuevo("Alumno", "Test", "titulacionTest", 0);
		
		System.out.println("Cambiando titulación...");
		alumno.cambiarTitulacion("Derecho");
		assertTrue(alumno.getTitulacion() == "Derecho");
	}

}
