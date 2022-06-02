package ej2_1upAST_aperona;

import java.util.*;

public class AsignacionAsientos {
	
	public static boolean grupoValido(ArrayList<String> grupo) {
		return (grupo.size() >= 2);
	}
	
	public static void añadirGrupo(ArrayList<String> grupo, ArrayList<String> asientosDisp) {
		if (grupoValido(grupo)) {
			for (String a : grupo)
				asientosDisp.add(a);
		}
		else System.out.println("No se ha podido añadir");	
	}
	
	
	
	
	public static void main(String[] args) {
		ArrayList<String> grupo1 = new ArrayList<String>();
		ArrayList<String> grupo2 = new ArrayList<String>();
		ArrayList<String> grupo3 = new ArrayList<String>();
		
		ArrayList<String> asientosTotales = new ArrayList<String>();
		
		grupo1.add("Antonio - Grupo 1");
		grupo1.add("Pepe - Grupo 1");
		
		grupo2.add("Maria - Grupo 2");
		
		grupo3.add("Mario - Grupo 3");
		grupo3.add("Guille - Grupo 3");
		grupo3.add("Alicia - Grupo 3");
		
		System.out.println("Añadimos grupo 1");
		añadirGrupo(grupo1, asientosTotales);
		System.out.println(asientosTotales);
		System.out.println("Añadimos grupo 2");
		añadirGrupo(grupo2, asientosTotales);
		System.out.println(asientosTotales);
		System.out.println("Añadimos grupo 3");
		añadirGrupo(grupo3, asientosTotales);
		System.out.println(asientosTotales);

	}

}
