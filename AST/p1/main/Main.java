package main;

import alumnos.*;
import array.*;

/**
 * @author aperona
 *
 */

public class Main {

	
	public static void main(String[] args) {
		
		//Creamos la cantidad de alumnos necesarios
		Nuevo alumnoNuevo1 = new Nuevo("Antonio", "Perona Martínez", "Ing. Telemática", 1);
		Nuevo alumnoNuevo2 = new Nuevo("Alberto", "Perez Martínez", "Ing. Sistemas", 2);
		
		Repetidor alumnoRepetidor1 = new Repetidor("Pepe", "Jhonson Jhonson", "Derecho", 3);
		Repetidor alumnoRepetidor2 = new Repetidor("Olga", "Martínez Casais", "Derecho", 4);
		Repetidor alumnoRepetidor3 = new Repetidor("Maria", "Valle Jhonson", "Historia", 5);
		
		Erasmus alumnoErasmus1 = new Erasmus("Jake", "Jhonson Jhonson", "Periodismo", 6);
		
		
		System.out.println("--------------------------------------");
		System.out.println("Prueba de int precio(int tipoalumno):");
		System.out.println(alumnoNuevo1.precio(0));
		System.out.println("--------------------------------------");
		System.out.println();
		
		//Probamos que podemos obtener cualquier informacion de cualquier alumno:
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Prueba del metodo infoToString(): ");
		System.out.println(alumnoRepetidor2.infoToString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		//Ahora cambiamos la titulacion de este alumno
		System.out.println();
		System.out.println("--------------------------------------");
		alumnoRepetidor2.cambiarTitulacion("Ingenieria de Materiales");
		System.out.println("Prueba del metodo infoToString() tras cambiar titulacion: ");
		System.out.println(alumnoRepetidor2.infoToString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		// creamos un array de alumnos con 6 posiciones
		ArrayAlumnos arrayAlumnos = new ArrayAlumnos(6);
		
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Probamos el método size() del array:");
		System.out.println("Size de arrayAlumnos: " + arrayAlumnos.size());
		System.out.println("--------------------------------------");
		System.out.println();
		
		
		// añadimos los alumnos al array
		arrayAlumnos.add(alumnoNuevo1);
		arrayAlumnos.add(alumnoNuevo2);
		arrayAlumnos.add(alumnoRepetidor1);
		arrayAlumnos.add(alumnoRepetidor2);
		// arrayAlumnos.add(alumnoRepetidor3); --> lo añadiremos luego con el método ins()
		arrayAlumnos.add(alumnoErasmus1);
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Una vez añadidos todos los alumnos menos uno, pintamos el array:");
		System.out.println(arrayAlumnos.toString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Añadimos el alumnoRepetidor3 en una posicion llena con ins");
		System.out.println("Output: " + arrayAlumnos.ins(3, alumnoRepetidor3));
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Añadimos el alumnoRepetidor3 en una posicion vacia con ins");
		System.out.println("Output: " + arrayAlumnos.ins(5, alumnoRepetidor3));
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Nuevo arrayAlumnos:");
		System.out.println(arrayAlumnos.toString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Probamos el método get con indice 3");
		Alumno alumnoGet = arrayAlumnos.get(3);
		System.out.println("Alumno obtenido con con get: " + alumnoGet.toString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Titulaciones antes de cambiarlas:");
		System.out.println(arrayAlumnos.titulacionesToString());
		System.out.println("Cambiamos todas las titulaciones de los alumnos a Ing. Telemática");
		arrayAlumnos.cambiarTitulaciones("Ing.Telemática");
		System.out.println(arrayAlumnos.titulacionesToString());
		System.out.println("--------------------------------------");
		System.out.println();
		
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Probamos el método getPrecioTotal");
		System.out.println("El precio total es de " + arrayAlumnos.getPrecioTotal() + " euros.");
		System.out.println("--------------------------------------");
		System.out.println();
		
		
	}

}
