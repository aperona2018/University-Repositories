# P4-IO - Ampliación de Sistemas Telemáticos.
## Antonio Perona Martínez - @aperona2018
## Ingeniería Telemática

## Especificaciones:

Programa realizado en JavaSE 13
Indentación - 4 espacios por tabulación

## Programa: Explicación

En esta práctica he creado una aplicación que registra alumnos, profesores, fechas, tutorías... y las escribe y lee de ficheros a través de las librerías java.nio y java.io.

## Paquetes: Especificaciones

### Paquetes usados también en la práctica 2: alumnos, profesores, fechas, tutorias, colatutorias

En esta práctica tendremos los mismos paquetes que usamos en la práctica 2: **alumnos**, **profesores**, **fechas**, **tutorias**, **colatutorias**... En los cuales no hay mucha novedad. El único cambio ha sido añadirles un método llamado **infoParse()** el cual genera un string en un formato facil de leer de fichero con toda la información del objeto creado. Por ejemplo el de la clase Alumno sería:

```java
public String infoParse() {
		String info = "/";  // '/' sera el comienzo de un nuevo alumno
		info += this.getPrioridad() + ";"; // luego ira el tipo
		info += this.getNombre() + ";";
		info += this.getApellidos() + ";";
		info += this.getTitulacion() + ";";
		info += this.getExpediente() + ";";
		return info;
	}
```

Donde se puede observar que cada alumno se separa por el caracter '/', con posteriormente los valores de este en orden separados por el caracter ';'.

Además, en este caso, también tenemos el método **infoParseJSON** a petición del **reto1upAST**, en el cual nos pedían introducir los datos en formato JSON.

```java
public String infoParseJSON() {
		String str = "{";
		str += "\n\t'nombre': " + this.getNombre();
		str += "\n\t'apellidos': " + this.getApellidos();
		str += "\n\t'titulacion': " + this.getTitulacion();
		str += "\n\t'expediente': " + this.getExpediente();
		str += "\n\t'prioridad': " + this.getPrioridad() + "\n}";
		return str;
	}
```

### Paquetes creados en esta nueva práctica: aplicacion y manejoFicheros

En esta práctica he creado la aplicación mencionada anteriormente y la clase **ManejadorFicheros** necesaria para el trato de ficheros respecto a la aplicación.

#### Clase ManejadorFicheros

Clase creada para, como su nombre indica, manejar los ficheros pertinentes a gusto del usuario de la aplicación. Usaremos las librerías **java.io** y **java.nio**.

Los métodos más relevantes de esta clase son:

- **escribirFichero(String fichero, String datos)**: En este método escribiremos el string con los datos en el fichero utilizando la librería **java.io**.

```java
public void escribirFichero(String fichero, String datos) throws IOException{
		
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
								new FileOutputStream(fichero)));
		
		out.writeUTF(datos);
		out.close();
	}
```

- **leerFichero(String fichero, Charset encoding)**: En este método leeremos del fichero obteniendo el path absoluto de este y leyendo los bytes de este. Más tarde, usando el charset utf-8, los decodificaremos para que sean un String y tratamos el string. **(Ya que todo fichero escrito empezará por el caracter '#')** En este método usaremos la librería **java.nio**, puesto que usamos File, Path, Charset... Propios de dicha librería.

```java
public String leerFichero(String fichero, Charset encoding) throws IOException {
		String filePath = new java.io.File(fichero).getAbsolutePath();
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		String str = new String(encoded, encoding);
		
		String str_aux = "";
		
		boolean comienzo = false;
		for (char c : str.toCharArray()) {
			if (c == '#') {
				comienzo = true;
				continue;
			}
			if (comienzo)
				str_aux += c;
		}
	
		return str_aux;
	}
```


#### Clase Aplicacion

Esta clase representará la aplicación a ejecutar y utilizar por los usuarios. 

Su funcionamiento se basa en enseñar las opciones posibles y pedir una opción al usuario. Con la opción seleccionada por el usuario, se llama al método respectivo de dicha tarea. Esto se repetirá hasta que el usuario decida salir de la aplicación. Esta clase posee como **atributos**:

- ArrayLists de **alumnos**, **fechas**, **profesores** donde se guardan los alumnos, fechas y profesores respectivamente creados.
- Una PriorityQueue ColaTutorias **colaTutorías** usada en la práctica 2, tendrá el mismo funcionamiento
- Un manejador de ficheros **manejadorFicheros** el cual se usará para escribir y leer de los ficheros respectivos.
- Un lector Scanner para capturar lo que se introduce por teclado por el usuario.

Las **opciones** de esta aplicación son:

1) Registrar alumno 
2) Registrar profesor 
3) Registrar fecha 
4) Añadir fecha a Alumno 
5) Añadir fecha a Profesor 
6) Registrar tutoría 
7) Pintar toda la información 
8) Guardar información en ficheros 
9) Cargar información de ficheros 
10) Salir 

Los **métodos** más significativos de esta Clase son:

- **guardarInfo()**: Guarda en ficheros toda la información referente al programa.

```java
public void guardarInfo() throws IOException{
		this.manejadorFicheros.escribirFichero(this.F_ALUMNOS_JSON, this.alumnosJSON());
		this.manejadorFicheros.escribirFichero(this.F_ALUMNOS,this.alumnosString());
		this.manejadorFicheros.escribirFichero(this.F_PROFESORES, this.profesoresString());
		this.manejadorFicheros.escribirFichero(this.F_FECHAS, this.fechasString());
		this.manejadorFicheros.escribirFichero(this.F_TUTORIAS, this.tutoriasString());
}
```

- **cargarInfo()**: Lee de los ficheros y posteriormente, trata los valores de los ficheros.

```java
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
```

- Los métodos de tratamiento de información a partir de los strings leídos de ficheros:

* tratarInfoAlumnosJSON(String alumnosJSON);
* tratarInfoProfesores(String profesores);
* tratarInfoFechas(String fechas);
* tratarInfoAlumnos(String alumnos);
* tratarInfoTutorias(String tutorias);

En estos métodos se usan Split para separar cada objeto del string y por cada objeto, se vuelve a separar en valores y se utilizan estos valores para crear de nuevo los objetos.
El método más completo es **tratarInfoTutorias(String infoTutorias)**:

```java
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
```

(Utilizo el if tutoria.length() == 1 porque puede haber fallos en la forma de la escritura de los objetos. De esta forma evitamos fallos en la creación de nuevo de estos objetos)


### Paquete main

#### Clase Main:

Clase principal del programa. En esta clase crearemos la aplicación y la ejecutaremos.








