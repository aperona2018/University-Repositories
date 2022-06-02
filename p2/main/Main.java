package main;

import alumnos.*;
import java.util.ArrayList;
import profesores.*;
import fechas.*;
import tutorias.*;


/**
 * 
 * @author aperona2018
*/

/**
 * Clase Main, donde probaremos la funcionalidad del programa.
 */

public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		// Creamos grupos de alumnos y de profesores
		ArrayList<Alumno> grupoDeAlumnos = new ArrayList<Alumno>();
		ArrayList<Profesor> grupoDeProfesores = new ArrayList<Profesor>();		
		// Creamos la cola de tutorias
		ColaTutorias colaTutorias = new ColaTutorias();
		
		// Creamos varios alumnos y los guardamos en su grupo
		Alumno alumno1 = new Erasmus("Alumno", "Erasmus 1", "Ing. Telematica", 1);
		Alumno alumno2 = new Repetidor("Alumno", "Repetidor 1", "Derecho", 2);
		Alumno alumno3 = new Repetidor("Alumno", "Repetidor 2", "Historia", 3);
		Alumno alumno4 = new Nuevo("Alumno", "Nuevo 1", "ADE", 4);
		Alumno alumno5 = new Nuevo("Alumno", "Nuevo 2", "ADE", 5);
		grupoDeAlumnos.add(alumno1);
		grupoDeAlumnos.add(alumno2);
		grupoDeAlumnos.add(alumno3);
		grupoDeAlumnos.add(alumno4);
		grupoDeAlumnos.add(alumno5);
		System.out.println("Grupo de Alumnos: ");
		System.out.println(grupoDeAlumnos);
		
		// Creamos varios profesores y los guardamos en su grupo
		Profesor profesor1 = new Profesor("Profesor", " 1", "AST", "D123");
		Profesor profesor2 = new Profesor("Profesor", " 2", "Programacion", "D123");
		grupoDeProfesores.add(profesor1);
		grupoDeProfesores.add(profesor2);
		System.out.println("\nGrupo de Profesores: ");
		System.out.println(grupoDeProfesores);
		
		// Creamos varias fechas
		Fecha f1 = new Fecha(10, 17, 3, 2022); // 10 H - 17/3/2022
		Fecha f2 = new Fecha(11, 17, 3, 2022); // 11 H - 17/3/2022
		Fecha f3 = new Fecha(12, 17, 3, 2022); // 12 H - 17/3/2022
		Fecha f4 = new Fecha(13, 17, 3, 2022); // 13 H - 17/3/2022
		Fecha f5 = new Fecha(14, 17, 3, 2022); // 14 H - 17/3/2022
		Fecha f6 = new Fecha(15, 17, 3, 2022); // 15 H - 17/3/2022

		
		// Añadimos las fechas a las fechas disponibles
		alumno1.añadirFechaDisponible(f1);
		alumno2.añadirFechaDisponible(f1);
		alumno2.añadirFechaDisponible(f2);
		alumno3.añadirFechaDisponible(f3);
		alumno4.añadirFechaDisponible(f4);
		alumno4.añadirFechaDisponible(f5);
		alumno5.añadirFechaDisponible(f6);
		
		profesor1.añadirFechaDisponible(f1);
		profesor1.añadirFechaDisponible(f2);
		profesor2.añadirFechaDisponible(f3);
		profesor2.añadirFechaDisponible(f4);
		profesor2.añadirFechaDisponible(f6);
		
		// Pintamos las fechas disponibles
		System.out.println("\nFechas disponibles:");
		System.out.println("----------------------\nAlumnos:\n");
		for (Alumno a: grupoDeAlumnos) {
			System.out.println(a + " ---> " + a.getFechasDisponibles());
		}
		
		System.out.println("----------------------\nProfesores:\n");
		for (Profesor p: grupoDeProfesores) {
			System.out.println(p + " ---> " + p.getFechasDisponibles());
		}
		System.out.println("-----------------------");
		
		// Creamos las tutorias
		Tutoria t1 = new Tutoria(1, alumno1, profesor1, f1, true, "Asunto1");
		Tutoria t2 = new Tutoria(2, alumno2, profesor1, f1, false, "Asunto2");
		Tutoria t3 = new Tutoria(3, alumno3, profesor2, f3, true, "Asunto3");
		Tutoria t4 = new Tutoria(4, alumno4, profesor2, f4, true, "Asunto4");
		Tutoria t5 = new Tutoria(5, alumno4, profesor1, f5, false, "Asunto5");
		Tutoria t6 = new Tutoria(6, alumno2, profesor1, f2, false, "Asunto6");
		Tutoria t7 = new Tutoria(7, alumno5, profesor2, f6, false, "Asunto7");
		
		// Añadimos las tutorias
		System.out.println("\nConcertamos tutorias: \n");
		System.out.println("-> Alumno 1 concerta una tutoria con Profesor1 a las 10 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t1);
		System.out.println("-> Alumno 2 concerta una tutoria con Profesor1 a las 10 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t2);
		System.out.println("-> Alumno 3 concerta una tutoria con Profesor2 a las 12 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t3);
		System.out.println("-> Alumno 4 concerta una tutoria con Profesor2 a las 13 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t4);
		System.out.println("-> Alumno 4 concerta una tutoria con Profesor1 a las 14 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t5);
		System.out.println("-> Alumno 2 concerta una tutoria con Profesor1 a las 11 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t6);
		System.out.println("-> Alumno 5 concerta una tutoria con Profesor2 a las 15 H - 17/3/2022: ");
		colaTutorias.concertarTutoria(t7);
		
		// Pintamos la cola de tutorias
		System.out.println("\nCola de Tutorias: OJO: La prioridad va en orden de izq a dcha\n");
		System.out.println(colaTutorias);
		
		// Cancelamos la tutoria 7 (Alumno Nuevo 2)
		System.out.println("\nEliminamos la tutoria 7 de la cola: ");
		colaTutorias.cancelarTutoria(t7);
		
		// Pintamos la nueva cola de tutorias
		System.out.println("\nnueva cola de Tutorias: OJO: La prioridad va en orden de izq a dcha\n");
		System.out.println(colaTutorias);
		
		
			
		
		
	}

}
