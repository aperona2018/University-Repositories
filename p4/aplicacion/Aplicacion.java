package aplicacion;

import java.util.*;
import manejoFicheros.*;
import alumnos.*;
import profesores.*;
import tutorias.*;
import fechas.*;
import java.io.*;
import java.nio.charset.*;

/**
 * @author aperona2018
*/

/**
 * Clase Aplicacion. Donde se realizará toda la práctica.
 * Se registrarán alumnos, profesores y fechas y se concertarán tutorías. Asimismo, 
 * se escribirán en los ficheros correspondientes para su posterior lectura.
 */

public class Aplicacion {
	private ArrayList<Alumno> listaAlumnos;
	private ArrayList<Profesor> listaProfesores;
	private ArrayList<Fecha> listaFechas;
	private ManejadorFicheros manejadorFicheros;
	private int numAlumnos = 0;
	private ColaTutorias colaTutorias;
	private boolean fin = false;
	private Scanner lector;
	
	private final int MINOPTS = 0;
	private final int MAXOPTS = 10;
	private final String F_ALUMNOS = "alumnos.txt";
	private final String F_ALUMNOS_JSON = "alumnosJSON.txt";
	private final String F_PROFESORES = "profesores.txt";
	private final String F_FECHAS = "fechas.txt";
	private final String F_TUTORIAS = "tutorias.txt";
	
	
	
	
	public Aplicacion() {
		this.colaTutorias = new ColaTutorias();
		this.lector = new Scanner(System.in);
		this.listaAlumnos = new ArrayList<Alumno>();
		this.listaProfesores = new ArrayList<Profesor>();
		this.listaFechas = new ArrayList<Fecha>();
		this.manejadorFicheros = new ManejadorFicheros();
	}
	
	
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}


	public ArrayList<Profesor> getListaProfesores() {
		return listaProfesores;
	}
	
	
	public void pintarListaAlumnos() {
		int i = 1;
		String lista = "Lista de alumnos: \n";
		if (this.listaAlumnos.isEmpty()) {
			lista += "Lista vacía \n";
		} else {
			for (Alumno a : this.listaAlumnos) {
				lista += i + ") " + a + "\n";
				i++;
			}
		}
		
		System.out.println(lista);
	}
	
	
	public void pintarListaProfesores() {
		int i = 1;
		String lista = "Lista de profesores: \n";
		if (this.listaProfesores.isEmpty()){
			lista += "Lista vacía \n";
		} else {
			for (Profesor p : this.listaProfesores) {
				lista += i + ") " + p + "\n";
				i++;
			}
		}
		
		System.out.println(lista);
	}
	
	
	public void pintarListaFechas() {
		int i = 1;
		String lista = "Lista de fechas: \n";
		if (this.listaFechas.isEmpty()) {
			lista += "Lista vacía \n";
		} else {
			for (Fecha f : this.listaFechas) {
				lista += i + ") " + f + "\n";
				i++;
			}
		}
		System.out.println(lista);
	}
	
	/**
	 * Pide un número mediante el scanner. Lo utilizaremos cada vez que queramos pedir una opción.
	 * @param min
	 * @param max
	 * @return opt
	 */
	public int pedirNum(int min, int max) {
		boolean valido = false;
		int opt = 0;
		String opcionInvalida = "Opción invalida, por favor, introduce una opción entre " + min + " y " + max;
		
		while (!valido) {
			try {
				System.out.print("\nIntroduce una opción: ");
				opt = this.lector.nextInt();
				if ((opt < min) || (opt > max))
					System.out.println(opcionInvalida);
				else valido = true;
			} 
			catch(Exception e) {
				System.out.println(opcionInvalida);
				this.lector.next();
			}
		}
		return opt;
	}
	

	/**
	 * Pintamos las opciones posibles de la aplicación y pedimos la opción al usuario.
	 * @return opcion
	 * @throws Exception
	 */
	public int pedirOpcion() throws Exception{
		int opcion = 0;
		String opciones = "\nOpciones disponibles: \n" ;
		opciones += "1) Registrar alumno \n";
		opciones += "2) Registrar profesor \n";
		opciones += "3) Registrar fecha \n";
		opciones += "4) Añadir fecha a Alumno \n";
		opciones += "5) Añadir fecha a Profesor \n";
		opciones += "6) Registrar tutoría \n";
		opciones += "7) Pintar toda la información \n";
		opciones += "8) Guardar información en ficheros \n";
		opciones += "9) Cargar información de ficheros \n";
		opciones += "10) Salir \n";
		
		System.out.println(opciones);
		opcion = this.pedirNum(this.MINOPTS, this.MAXOPTS);
			
		return opcion;
	}
	
	
	public String pedirNombre() {
		System.out.print("\nIntroduce nombre: ");
		this.lector.nextLine();
		return this.lector.nextLine();
	}
	
	
	public String pedirApellidos() {
		System.out.print("\nIntroduce apellidos: ");
		return this.lector.nextLine();
	}
	
	
	public String pedirTitulacion() {
		System.out.print("\nIntroduce titulacion: ");
		return this.lector.nextLine();
	}
	
	public String pedirAsignatura() {
		System.out.print("\nIntroduce asignatura: ");
		return this.lector.nextLine();
	}
	
	public String pedirDespacho() {
		System.out.print("\nIntroduce despacho: ");
		return this.lector.nextLine();
	}
	
	
	/**
	 * Dados los parámetros, se crea un alumno y se añade a la lista de alumnos de la aplicación
	 * 
	 * 
	 * @param tipo
	 * @param nombre
	 * @param apellidos
	 * @param titulacion
	 * @param expediente
	 * @return El alumno se ha creado o no
	 */
	
	public boolean crearAlumno(int tipo, String nombre, String apellidos, String titulacion , int expediente){
		Alumno alumno;
		switch (tipo) {
			case 2:
				alumno = new Nuevo(nombre, apellidos, 
									titulacion, expediente);
				break;
			case 1:
				alumno = new Erasmus(nombre, apellidos, 
						titulacion, expediente);
				break;
			case 0:
				alumno = new Repetidor(nombre, apellidos, 
						titulacion, expediente);
				break;
			default: System.out.println("ERROR: Fallo en creación de alumno.");
				return false;
		}
		this.listaAlumnos.add(alumno);
		System.out.println("Alumno " + alumno + " añadido.");
		return true;
	}

	/**
	 * Se piden los parámetros para crear un nuevo alumno y se llama a crearAlumno()
	 */
	public void registrarAlumno() {
		boolean valido = false;
		while (!valido) {
			try {
				System.out.print("Introduce tipo de alumno: (N = 2) (E = 1) (R = 0): ");
				int tipoAlumno = this.lector.nextInt();
				
				String nombreAlumno = this.pedirNombre();
				String apellidosAlumno = this.pedirApellidos();
				String titulacionAlumno = this.pedirTitulacion();
					
				System.out.println("TRAZA - Nombre: " + nombreAlumno);
				System.out.println("TRAZA - Apellidos: " + apellidosAlumno);
				System.out.println("TRAZA - Titulacion: " + titulacionAlumno);
	
				valido = this.crearAlumno(tipoAlumno, nombreAlumno, apellidosAlumno, titulacionAlumno, this.numAlumnos++);
			}
			catch (Exception e) { //TODO mirar tipo excepción
				System.out.println("ERROR: Introduce bien los tipos");
				this.lector.next();
			}
		}
	}
	
	
	/**
	 * Dados los parámetros, crea un nuevo profesor y lo añade a la lista de profesores
	 * @param nombre
	 * @param apellidos
	 * @param asignatura
	 * @param despacho
	 * @return si el profesor se ha añadido o no
	 */
	public boolean crearProfesor(String nombre, String apellidos, String asignatura,
			String despacho) {
		Profesor profesor = new Profesor(nombre, apellidos, asignatura, despacho);
		System.out.println("\nProfesor " + profesor + " creado.");
		return this.listaProfesores.add(profesor);
	}


	/**
	 * Se piden los parámetros del profesor a crear y se llama a crearProfesor()
	 */
	public void registrarProfesor() {
		boolean valido = false;
		while (!valido) {
			try {
				String nombreProf = this.pedirNombre();
				String apellidosProf = this.pedirApellidos();
				String asignaturaProf = this.pedirAsignatura();
				String despachoProf = this.pedirDespacho();
				
				System.out.println("TRAZA - Nombre: " + nombreProf);
				System.out.println("TRAZA - Apellidos: " + apellidosProf);
				System.out.println("TRAZA - Asignatura: " + asignaturaProf);
				System.out.println("TRAZA - Despacho: " + despachoProf);
				
				valido = this.crearProfesor(nombreProf, apellidosProf, asignaturaProf, despachoProf);
				
			}
			catch (Exception e) {
				System.out.println("ERROR: Introduce bien los tipos");
				this.lector.next();
			}
		}
		
	}
	
	
	/**
	 * Dados los parámetros de la fecha, comprueba que es válida
	 * @param hora
	 * @param dia
	 * @param mes
	 * @param año
	 * @return
	 */
	public boolean comprobarFecha(int hora, int dia, int mes, int año) {
		boolean horaValida = ((hora > 0) && (hora <= 24));
		boolean diaValido = ((dia > 0) && (dia <= 31)); //Tendremos en cuenta que cada mes tiene 31 días
		boolean mesValido = ((mes > 0) && (mes <= 12));
		boolean añoValido = ((año >= 2022) && (año <= 2023)); // tendremos en cuenta que la aplicación será para los años 2022 - 2023
		
		boolean valido = ((horaValida) && (diaValido) && (mesValido) && (añoValido));
		
		if (!valido) System.out.println("Fecha inválida.");
		
		return valido;
		
	}
	
	
	/**
	 * Dados los parámetros de la fecha, la crea y la añade a la lista de fechas
	 * @param hora
	 * @param dia
	 * @param mes
	 * @param año
	 */
	public void crearFecha(int hora, int dia, int mes, int año) {
		Fecha fecha = new Fecha(hora, dia, mes, año);
		this.listaFechas.add(fecha);
		System.out.println("\nFecha " + fecha + " creada.");
	}
	
	
	/**
	 * Pide los parámetros necesarios para crear la fecha, comprueba que sean válidos y 
	 * crea la fecha.
	 */
	public void registrarFecha() {
		boolean valido = false;
		int hora = 0;
		int dia = 0;
		int mes = 0;
		int año = 0;
		while (!valido) {
			try {
				System.out.print("Introduzca hora: ");
				hora = this.lector.nextInt();
				System.out.print("Introduzca dia: ");
				dia = this.lector.nextInt();
				System.out.print("Introduzca mes: ");
				mes = this.lector.nextInt();
				System.out.print("Introduzca año: ");
				año = this.lector.nextInt();

				valido = this.comprobarFecha(hora, dia, mes, año);
			}
			catch (Exception e) {
				System.out.println("ERROR: Introduce bien los tipos");
				this.lector.next();
			}
		}
	
		this.crearFecha(hora, dia, mes, año);
	}
	
	
	public Alumno pedirAlumno() {
		if (this.listaAlumnos.isEmpty())
			return null;
		this.pintarListaAlumnos();
		System.out.print("\nSeleccione índice del alumno: ");
		int opt = this.pedirNum(1, this.listaAlumnos.size());
		Alumno alumn = this.listaAlumnos.get(opt-1);
		return alumn;
	}
	
	
	public Profesor pedirProfesor() {
		if (this.listaProfesores.isEmpty())
			return null;
		this.pintarListaProfesores();
		System.out.print("\nSeleccione índice del profesor: ");
		int opt = this.pedirNum(1, this.listaProfesores.size());
		Profesor prof = this.listaProfesores.get(opt-1);
		return prof;
	}
	
	
	public Fecha pedirFecha() {
		if (this.listaFechas.isEmpty())
			return null;
		this.pintarListaFechas();
		System.out.print("\nSeleccione índice de la fecha: ");
		int opt = this.pedirNum(1, this.listaFechas.size());
		Fecha fecha = this.listaFechas.get(opt-1);
		return fecha;
	}
	
	
	public boolean pedirPresencial() {
		System.out.print("\n¿Quieres que sea presencial? (No = 0), (Si = 1) : ");
		int opt = this.pedirNum(0, 1);
		if (opt == 0)
			return false;
		else return true;
	}
	
	
	public String pedirAsunto() {
		System.out.print("\nIntroduce Asunto: ");
		this.lector.nextLine();
		return this.lector.nextLine();
	}
	
	
	/**
	 * Dados los parámetros, crea una tutoría
	 * 
	 * @param numTutoria
	 * @param a
	 * @param p
	 * @param f
	 * @param presencial
	 * @param asunto
	 * @return tutoria creada
	 */
	public Tutoria crearTutoria(int numTutoria, Alumno a, Profesor p, Fecha f, boolean presencial, String asunto) {
		
		Tutoria t = new Tutoria(numTutoria, a, p, f, presencial, asunto);
		
		return t;
	}
	
	
	/**
	 * Se piden los parámetros necesarios y se crea y concerta la tutoría
	 */
	public void registrarTutoria() {
		boolean valido = false;
		while (!valido) {
			try {
				Alumno a = this.pedirAlumno();
				Profesor p = this.pedirProfesor();
				System.out.println("Fechas disponibles:");
				System.out.print(a + " --> ");
				System.out.println(a.getFechasDisponibles());
				System.out.print(p + " --> ");
				System.out.println(p.getFechasDisponibles());
				Fecha f = this.pedirFecha();
				boolean presencial = this.pedirPresencial();
				String asunto = this.pedirAsunto();
				Tutoria t = this.crearTutoria(this.colaTutorias.size(), a, p, f, presencial, asunto);
				this.colaTutorias.concertarTutoria(t);
				System.out.println("TRAZA: " + t.infoToString());
				valido = true;
			}
			catch (Exception e) {
				System.out.println("ERROR: Introduce bien los tipos");
				this.lector.next();
			}
		}
	}
	
	public void añadirFechaAlumno() {
		try {
			Alumno a = this.pedirAlumno();
			Fecha f = this.pedirFecha();
			
			a.añadirFechaDisponible(f);
		} catch (NullPointerException e) {
			System.out.println("ERROR: No hay alumnos registrados");
		}
	}
	
	public void añadirFechaProfesor() {
		try {
		Profesor p = this.pedirProfesor();
		Fecha f = this.pedirFecha();
		
		p.añadirFechaDisponible(f);
		} catch (NullPointerException e) {
			System.out.println("ERROR: No hay profesores registrados");
		}
	}
	
	
	public void pintarInfo() {
		System.out.println("Alumnos registrados: " + this.listaAlumnos);
		System.out.println("Profesores registrados: " + this.listaProfesores);
		System.out.println("Fechas registradas: " + this.listaFechas);
		System.out.println("Cola de tutorías: " + this.colaTutorias);
	}
	
	
	public String alumnosString() {
		String string = "#";
		for (Alumno a : this.listaAlumnos) {
			string += a.infoParse();
		}
		
		return string;
	}
	
	/**
	 * Reutorna un string con todos los datos de la lista de alumnos en formato JSON
	 * @return String de los alumnos en formato JSON
	 */
	public String alumnosJSON() {
		Iterator<Alumno> it = this.listaAlumnos.iterator();
		String string = "#";  //para marcar el comienzo de fichero
		while (it.hasNext()){
			Alumno a = it.next();
			string += a.infoParseJSON();
			if (it.hasNext())
				string += "\n,";
		}
		return string;
	}
	
	
	public String profesoresString() {
		String string = "\n#=";
		for (Profesor p : this.listaProfesores) {
			string += p.infoParse();
		}
		
		return string;
	}
	
	
	public String fechasString() {
		String string = "\n#=";
		for (Fecha f : this.listaFechas) {
			string += f.infoParse();
		}
		
		return string;
	}
	
	
	public String tutoriasString() {
		String string = "\n#=";
		for (Tutoria t : this.colaTutorias.getColaTutorias()) {
			string += t.infoParse();
		}
		
		return string;
	}
	
	
	/**
	 * Escribe en el fichero correspondiente mediante el manejador de ficheros la información
	 * pertinente.
	 * @throws IOException
	 */
	public void guardarInfo() throws IOException{
		this.manejadorFicheros.escribirFichero(this.F_ALUMNOS_JSON, this.alumnosJSON());
		this.manejadorFicheros.escribirFichero(this.F_ALUMNOS,this.alumnosString());
		this.manejadorFicheros.escribirFichero(this.F_PROFESORES, this.profesoresString());
		this.manejadorFicheros.escribirFichero(this.F_FECHAS, this.fechasString());
		this.manejadorFicheros.escribirFichero(this.F_TUTORIAS, this.tutoriasString());
	}
	
	
	/**
	 * Dados los valores del formato JSON, los separa y trata
	 * @param valores
	 */
	public void tratarValoresAlumnoJSON(String[] valores) {
		if (valores[0].contains("nombre"))
			System.out.println("Nombre del alumno: " + valores[1]);
		else if (valores[0].contains("apellidos"))
			System.out.println("Apellidos del alumno: " + valores[1]);
		else if (valores[0].contains("titulacion"))
			System.out.println("Titulacion del alumno: " + valores[1]);
		else if (valores[0].contains("expediente"))
			System.out.println("Expediente del alumno: " + valores[1]);
		else if (valores[0].contains("prioridad"))
			System.out.println("Prioridad del alumno: " + valores[1]);
	}
	

	/**
	 * Dado el string completo en formato JSON, los separa por alumnos y llama a 
	 * tratarValoresAlumnoJSON()
	 * @param infoAlumnos
	 */
	public void tratarInfoAlumnosJSON(String infoAlumnos) {
		String[] alumnosSeparados = infoAlumnos.split(",");
		
		for (String alumno : alumnosSeparados) {
			System.out.println("\nComienzo de un nuevo alumno:");
			String[] campos = alumno.split("\n");
			for (String campo : campos) {
				if (campo.contains("{") || campo.contains("}"))
					continue;
				else {
					String[] valores = campo.split(":");
					this.tratarValoresAlumnoJSON(valores);
				}
			}
		}
	}
	
	
	/**
	 * Trata la información del String de infoAlumnos
	 * @param infoAlumnos
	 */
	public void tratarInfoAlumnos(String infoAlumnos) {
		String[] alumnSeparados = infoAlumnos.split("/");
		for (String alumno : alumnSeparados) {
			if (alumno.length() == 0)
				continue;
			else {
				String[] valoresSeparados = alumno.split(";");
				int prioridad = Integer.parseInt(valoresSeparados[0]);
				String nombre = valoresSeparados[1];
				String apellidos = valoresSeparados[2];
				String titulacion = valoresSeparados[3];
				int expediente = Integer.parseInt(valoresSeparados[4]);
				this.crearAlumno(prioridad, nombre, apellidos, titulacion, expediente); 
			}
		}
	}
	
	
	/**
	 * Trata la información del String de infoProfesores
	 * @param infoProfesores
	 */
	public void tratarInfoProfesores(String infoProfesores) {
		String[] profSeparados = infoProfesores.split("/");
		for (String profesor : profSeparados) {
			if (profesor.length() == 1)
				continue;
			else {
				String[] valoresSeparados = profesor.split(";");
				String nombre = valoresSeparados[0];
				String apellidos = valoresSeparados[1];
				String asignatura = valoresSeparados[2];
				String despacho = valoresSeparados[3];
				this.crearProfesor(nombre, apellidos, asignatura, despacho);
			}
		}
	}
	
	
	/**
	 * Trata la informacion del String de infoFechas
	 * @param infoFechas
	 */
	public void tratarInfoFechas(String infoFechas) {
		String[] fechasSeparadas = infoFechas.split("/");
		for (String fecha : fechasSeparadas) {
			if (fecha.length() == 1)
				continue;
			else {
				String[] valoresSeparados = fecha.split(";");
				int hora = Integer.parseInt(valoresSeparados[0]);
				int dia = Integer.parseInt(valoresSeparados[1]);
				int mes = Integer.parseInt(valoresSeparados[2]);
				int año = Integer.parseInt(valoresSeparados[3]);
				this.crearFecha(hora, dia, mes, año);
				
			}
		}
	}
	
	public Alumno encontrarAlumno(int expediente) {
		Alumno alumn = null;
		for (Alumno a : this.listaAlumnos) {
			if (a.getExpediente() == expediente)
				alumn = a;
		}
		return alumn;
	}
	
	public Profesor encontrarProfesor(String despacho) {
		Profesor prof = null;
		for (Profesor p : this.listaProfesores) {
			if (p.getDespacho().equals(despacho))
				prof = p;
		}
		return prof;
	}
	
	public Fecha encontrarFecha(String fechaString) {
		Fecha fecha = null;
		for (Fecha f : this.listaFechas) {
			if (f.toString().equals(fechaString))
				fecha = f;
		}
		return fecha;
	}
	
	
	/**
	 * Trata la información del String infoTutorias
	 * @param infoTutorias
	 */
	public void tratarInfoTutorias(String infoTutorias) {
		String[] tutoriasSeparadas = infoTutorias.split("&");
		for (String tutoria : tutoriasSeparadas) {
			if (tutoria.length() == 1)
					continue;
			else {
				String[] valoresSeparados = tutoria.split(";");
				int numTut = Integer.parseInt(valoresSeparados[0]);
				
				int expAlumno = Integer.parseInt(valoresSeparados[1]);
				Alumno alumno = this.encontrarAlumno(expAlumno);
				
				String despachoProf = valoresSeparados[2];
				Profesor profesor = this.encontrarProfesor(despachoProf);
				
				String fechaString = valoresSeparados[3];
				Fecha fecha = this.encontrarFecha(fechaString);
				
				alumno.añadirFechaDisponible(fecha);
				profesor.añadirFechaDisponible(fecha);
				
				
				boolean presencial = Boolean.valueOf(valoresSeparados[4]);
				String asunto = valoresSeparados[5];
				
				Tutoria t = this.crearTutoria(numTut, alumno, profesor, fecha, presencial, asunto);
				this.colaTutorias.concertarTutoria(t);
			}
		}
		
	}
	

	/**
	 * Carga la información de los ficheros pertinentes usando el manejador 
	 * de ficheros y la trata
	 * @throws IOException
	 */
	public void cargarInfo() throws IOException{
		
		Charset charset = this.manejadorFicheros.getCHARSET();
		
		String alumnosJSON = this.manejadorFicheros.leerFichero(this.F_ALUMNOS_JSON, charset);
		String alumnos = this.manejadorFicheros.leerFichero(this.F_ALUMNOS, charset);
		String profesores = this.manejadorFicheros.leerFichero(this.F_PROFESORES, charset);
		String fechas = this.manejadorFicheros.leerFichero(this.F_FECHAS, charset);
		String tutorias = this.manejadorFicheros.leerFichero(this.F_TUTORIAS, charset);
		
		System.out.println("ALUMNOSJSON: " + alumnosJSON);
		
		this.tratarInfoAlumnosJSON(alumnosJSON);
		this.tratarInfoProfesores(profesores);
		this.tratarInfoFechas(fechas);
		this.tratarInfoAlumnos(alumnos);
		this.tratarInfoTutorias(tutorias);
		this.numAlumnos = this.listaAlumnos.size();
		
	}
	
	
	/**
	 * Guarda la información en ficheros y sale del programa
	 * @throws IOException
	 */
	public void salir() throws IOException{
		this.fin = true;
		this.lector.close();
		this.guardarInfo();
		String guardarText = "\n==============================\n";
		guardarText += "Contenido guardado en memoria! \n==============================\n";
		System.out.println(guardarText);
		System.out.println("Saliendo de la aplicación...");
	}
		
	
	/**
	 * Dada una opción, la maneja llamando al método pertinente.
	 * 
	 * @param opcion
	 * @throws IOException
	 */
	public void manejarOpcion(int opcion) throws IOException{
		
		switch(opcion) {
		
			case 1:
				String registrarAlumnoText = "\n==============================\n";
				registrarAlumnoText += "Registrar alumno: \n==============================\n";
				System.out.println(registrarAlumnoText);
				this.registrarAlumno();
				break;
			case 2:
				String registrarProfesorText = "\n==============================\n";
				registrarProfesorText += "Registrar profesor: \n==============================\n";
				System.out.println(registrarProfesorText);
				this.registrarProfesor();
				break;
			case 3:
				String registrarFechaText = "\n==============================\n";
				registrarFechaText += "Registrar Fecha: \n==============================\n";
				System.out.println(registrarFechaText);
				this.registrarFecha();
				break;
			case 4:
				String añadirFechaAlumnText = "\n==============================\n";
				añadirFechaAlumnText += "Añadir Fecha a Alumno: \n==============================\n";
				System.out.println(añadirFechaAlumnText);
				this.añadirFechaAlumno();
				break;
			case 5:
				String añadirFechaProfText = "\n==============================\n";
				añadirFechaProfText += "Añadir Fecha a Profesor: \n==============================\n";
				System.out.println(añadirFechaProfText);
				this.añadirFechaProfesor();
				break;
			case 6:
				String registrarTutoriaText = "\n==============================\n";
				registrarTutoriaText += "Registrar Tutoria: \n==============================\n";
				System.out.println(registrarTutoriaText);
				this.registrarTutoria();
				break;
			case 7:
				String pintarInfoText = "\n==============================\n";
				pintarInfoText += "Información de la aplicación: \n==============================\n";
				System.out.println(pintarInfoText);
				this.pintarInfo();
				break;
			case 8:
				String guardarText = "\n==============================\n";
				guardarText += "Contenido guardado en memoria! \n==============================\n";
				System.out.println(guardarText);
				this.guardarInfo();
				break;
			case 9:
				String cargarText = "\n==============================\n";
				cargarText += "Contenido cargado de memoria: \n==============================\n";
				System.out.println(cargarText);
				this.cargarInfo();
				break;
			
			case 10:
				this.salir();
				break;
		}
	}
	
	
	/**
	 * Método para ejecutar la aplicación. Mientras no haya terminado, pide opciones y las trata.
	 * @throws Exception
	 */
	public void run() throws Exception{
		while (!this.fin) {
			int opt = this.pedirOpcion();
			this.manejarOpcion(opt);
		}
	}
	

	public static void main(String[] args) throws Exception{
		Aplicacion a = new Aplicacion();
		a.run();
	}
}
