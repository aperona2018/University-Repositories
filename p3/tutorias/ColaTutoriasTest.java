package tutorias;

import static org.junit.Assert.*;
import org.junit.Test;

import alumnos.Alumno;
import alumnos.Nuevo;
import fechas.Fecha;
import profesores.Profesor;

public class ColaTutoriasTest {
	
	
	@Test
	public void concertarConDisponibilidad() {
    	Alumno alumnTest = new Nuevo("Nuevo", "Test", "Telematica", 1);
    	Profesor profTest  = new Profesor("Profe", "Test", "AST", "D123");

    	Fecha f = new Fecha(9, 9, 12, 2018);
    	
    	alumnTest.añadirFechaDisponible(f);
    	profTest.añadirFechaDisponible(f);
    	
    	Tutoria tutoriaTest = new Tutoria(1, alumnTest, profTest, f, true, "AsuntoTest");
    	ColaTutorias colaTest = new ColaTutorias();
    	
    	System.out.println("Concertando una tutoría con disponibilidad...");
    	colaTest.concertarTutoria(tutoriaTest);
    	assertTrue(colaTest.getColaTutorias().size() == 1);
	}
	
	@Test
	public void concertarSinDisponibilidad() {
		Alumno alumnTest = new Nuevo("Nuevo", "Test", "Telematica", 1);
    	Profesor profTest  = new Profesor("Profe", "Test", "AST", "D123");

    	Fecha f = new Fecha(9, 9, 12, 2018);
    	
    	Tutoria tutoriaTest = new Tutoria(1, alumnTest, profTest, f, true, "AsuntoTest");
    	ColaTutorias colaTest = new ColaTutorias();
    	
    	System.out.println("Concertando una tutoría sin disponibilidad...");
    	colaTest.concertarTutoria(tutoriaTest);
    	assertTrue(colaTest.getColaTutorias().size() == 0);
	}
	
	@Test
	public void cancelarTutoria() {
		Alumno alumnTest = new Nuevo("Nuevo", "Test", "Telematica", 1);
    	Profesor profTest  = new Profesor("Profe", "Test", "AST", "D123");
    	Fecha f = new Fecha(9, 9, 12, 2018);
    	
    	alumnTest.añadirFechaDisponible(f);
    	profTest.añadirFechaDisponible(f);
    	
    	Tutoria tutoriaTest = new Tutoria(1, alumnTest, profTest, f, true, "AsuntoTest");
    	ColaTutorias colaTest = new ColaTutorias();
    	
    	colaTest.concertarTutoria(tutoriaTest);
    	
    	System.out.println("Cancelando tutoría...");
    	colaTest.cancelarTutoria(tutoriaTest);
    	assertTrue(colaTest.getColaTutorias().size() == 0);
	}
	
	@Test
	public void comprobandoColision() {
		Alumno alumnTest1 = new Nuevo("Nuevo", "Test", "Telematica", 1);
		Alumno alumnTest2 = new Nuevo("Nuevo2", "Test", "Telematica", 2);
    	Profesor profTest  = new Profesor("Profe", "Test", "AST", "D123");
    	Fecha f = new Fecha(9, 9, 12, 2018);
    	
    	alumnTest1.añadirFechaDisponible(f);
    	alumnTest2.añadirFechaDisponible(f);
    	profTest.añadirFechaDisponible(f);
    	
    	Tutoria tutoriaTest1 = new Tutoria(1, alumnTest1, profTest, f, true, "AsuntoTest");
    	Tutoria tutoriaTest2 = new Tutoria(2, alumnTest2, profTest, f, true, "AsuntoTest");
    	
    	System.out.println("Comprobando colision...");
    	ColaTutorias colaTest = new ColaTutorias();
    	colaTest.concertarTutoria(tutoriaTest1);
    	assertTrue(colaTest.comprobarColision(tutoriaTest2) == true);
	}
	
}
