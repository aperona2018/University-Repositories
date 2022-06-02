package profesores;

import static org.junit.Assert.*;
import org.junit.Test;
import fechas.Fecha;


public class ProfesorTest {

	@Test
	public void añadirFecha() {
		Profesor profesor = new Profesor("Profesor","Test", "asignaturaTest", "DespachoTest");
		Fecha fecha = new Fecha(9,23,3,2022);
		
		System.out.println("Añadiendo fecha disponible...");
		profesor.añadirFechaDisponible(fecha);
		assertTrue(profesor.getFechasDisponibles().size() == 1);
		
	}
	
	
	@Test
	public void eliminarFecha() {
		Profesor profesor = new Profesor("Profesor","Test", "asignaturaTest", "DespachoTest");
		Fecha fecha = new Fecha(9,23,3,2022);
		profesor.añadirFechaDisponible(fecha);
		
		System.out.println("Eliminando fecha disponible...");
		profesor.eliminarFechaDisponible(fecha);
		assertTrue(profesor.getFechasDisponibles().size() == 0);
	}
}
