# Ampliación de Sistemas Telemáticos - Práctica 2: Cola Prioridad
## Autor: Antonio Perona Martínez - @aperona2018
## Ingeniería Telemática

## 1.- Especificaciones del programa:

**- Versión de java:** JavaSE-13
**- Tabulación:** 4 espacios por tabulación

## 2.- Especificación de paquetes:

### 2.1) Paquete alumnos

Este paquete contiene las clases **Alumno**, **Nuevo**, **Erasmus** y **Repetidor**. 

Cada alumno dispone de los 5 atributos de la práctica 1:
- nombre: String
- apellidos: String
- precio: int
    * Nuevo = 60
    * Erasmus = 80
    * Repetidor = 70
- titulación: String
- expediente: int
Asimismo, se han añadido dos **nuevos atributos**:
- **prioridad**: - int - Se asigna una prioridad a cada tipo de alumno para su posterior asignación en la priorityQueue
    * Nuevo = 2
    * Erasmus = 1
    * Repetidor = 0
- **fechasDisponibles** : ArrayList de fechas en las cuales el alumno podrá tener una tutoría.


#### 2.1 - Paquete alumnos: Clase Alumno

Clase padre de los alumnos, en este paquete se definen los principales métodos que creamos en la práctica 1:

- **Getters y Setters** 

- **añadirFechaDisponible(Fecha fecha):** Añade una fecha a la lista de fechas disponibles del alumno

- **eliminarFechaDisponible(Fecha fecha):** Elimina una fecha de la lista de fechas disponibles del alumno

- **altEliminarFechaDisponible(Fecha fecha):** ACLARACIÓN: Había decidido crear este método para eliminar una fecha que, sin ser el mismo objeto que otra fecha ya establecida dentro de la lista de fechas disponibles, contenga la misma fecha en este. Es decir, objetos Fecha diferentes con mismo contenido y siendo, por ende, fechas idénticas. No obstante una vez aclarado con el profesor que solo
puede existir un objeto fecha con el mismo contenido, no lo uso en el programa. Sin embargo he decidido mantener el método por si acaso.
Retorna true si se ha podido eliminar y false si no.
      
```java
    public boolean altEliminarFechaDisponible(Fecha fecha) {
        // Creamos un iterador auxiliar
        Iterator<Fecha> it = this.fechasDisponibles.iterator();
		while (it.hasNext()) {
	        Fecha f = it.next();
		    if (f.equals(fecha)) {
			    this.fechasDisponibles.remove(f);
			    return true;
		    }	
	    }
		return false;
    }
```

**- precio(int tipoalumno):** Método implementado en la práctica 1, dado 0, 1 o 2 como entrada devuelve el precio de Nuevo, Repetidor y Erasmus respectivamente.

**- infoToString():** Retorna un String con la información pertinente del alumno.

**- toString():** Retorna un String con el nombre y apellidos del alumno.

**- cambiarTitulacion(String titulacion):** Cambia la titulación del alumno por la introducida en la entrada.

#### 2.1 - Paquete alumnos: Clases Nuevo, Erasmus y Repetidor.

Clases hijas de la clase Alumno, heredan todos los métodos y atributos de su clase padre. 

* Se diferencian en el precio, que se establece en su constructor.

### 2.2) Paquete profesores
#### 2.2 - Paquete profesores: Clase Profesor

Clase profesor, dispone de 5 atributos:

- nombre: String
- apellidos: String
- asignatura: String
- despacho: String
- fechasDisponibles: ArrayList de fechas en las cuales el profesor podrá tener una tutoría.

Los métodos que posee son:

- **getters y setters**

- **añadirFechaDisponible(Fecha fecha):** Añade una fecha a la lista de fechas disponibles del profesor

- **eliminarFechaDisponible(Fecha fecha):** Elimina una fecha de la lista de fechas disponibles del profesor

- **altEliminarFechaDisponible(Fecha fecha):** ACLARACIÓN: Había decidido crear este método para eliminar una fecha que, sin ser el mismo objeto que otra fecha ya establecida dentro de la lista de fechas disponibles, contenga la misma fecha en este. Es decir, objetos Fecha diferentes con mismo contenido y siendo, por ende, fechas idénticas. No obstante una vez aclarado con el profesor que solo
puede existir un objeto fecha con el mismo contenido, no lo uso en el programa. Sin embargo he decidido mantener el método por si acaso.
Retorna true si se ha podido eliminar y false si no. El snippet está en la clase Alumno

- **infoToString():** Retorna un String con la información pertinente del profesor.

- **toString():** Retorna un String con el nombre y apellidos del profesor.


### 2.3) Paquete fechas
#### 2.3 - Paquete fechas: Clase Fecha

He decidido crear mi propia clase Fecha, la cual contiene 4 atributos:

- hora: int
- dia: int
- mes: int
- año: int

Con los siguientes métodos:

- **Getters y Setters**

- **toString()**: Devuelve un String con la hora y fecha del objeto Fecha


### 2.4) Paquete tutorias
#### 2.4 - Paquete tutorias: Clase Tutoria

Esta clase posee una **inner class InfoTutoria**, la cual contiene 2 atributos:

- presencial: boolean
- asunto: string

Asimismo contiene los siguientes métodos:

- **Getters y Setters**

- **toString():** Retorna un string con la información pertinente de la clase

La clase Tutoria posee 6 atributos:

- numTutoria: int del número de la tutoría
- alumno: alumno que pide la tutoría
- profesor: profesor al que se le pide la tutoría
- lugar: String, el lugar será el despacho del profesor al que se le pide la tutoría
- infoTutoria: utiliza la inner class anterior para definir la información extra referente a la tutoría
- fecha: clase Fecha para definir la fecha de la tutoría

Asimismo, cuenta con los siguientes métodos:

- **Getters y Setters**

- **infoToString():** Devuelve un String con toda la información pertinente a la tutoría

- **toString():** Devuelve un String con el número de la tutoría y el alumno que la pide


#### 2.4 - Paquete tutorias: Clase ComparadorTutorias

Comparador que usaremos en la cola de tutorías

Solamente tiene un método:

- **compare(Tutoria t1, Tutoria t2):** Saca los alumnos de cada tutoria y compara su prioridad, retornando -1, 0 o 1 dependiendo de la prioridad.

```java
    @Override
    public int compare(Tutoria t1, Tutoria t2) {
    
    	Alumno a1 = t1.getAlumno();
    	Alumno a2 = t2.getAlumno();
    	
    	if (a1.getPrioridad() < a2.getPrioridad())
    		return 1;
    	else if (a1.getPrioridad() > a2.getPrioridad())
    		return -1;
    	else return 0;
    }
```


#### 2.4 - Paquete tutorias: Clase ColaTutorias:

Esta clase contiene una inner class de excepción llamada **ColissionException**, la cual se lanzará en caso de haber una colisión de fechas en el mismo lugar 
en la cola de tutorías.

La clase ColaTutorias contiene 2 atributos:

- colaTutorias: PriorityQueue de tutorías en el orden pedido en la práctica. Se le pasa el ComparadorTutorias en el constructor
- comparadorTutorias: comparador de tutorías

Asimismo, dispone de los siguientes métodos

- **toString():** retorna un String con las tutorías de la cola en sentido de **prioridad de izquierda a derecha**. Este método se apoya en una cola Auxiliar para recuperar los elementos de la cola principal tras añadirlos al String que devuelve.

```java
    public String toString() {
		/*
		 * Método colaToString(): Convierte a String los elementos de la cola, de
		 * izquierda a derecha en orden de prioridad. 
		 * 
		 * Este método se apoya en una cola Auxiliar para recuperar los elementos 
		 * de la cola principal tras añadirlos al String que devuelve.
		 * 
		 * ENTRADAS:
		 *  la cola que llama al método.
		 * SALIDAS:
		 *  cadena: String que contiene las tutorías de la cola.
		 */
		PriorityQueue<Tutoria> colaAux = new PriorityQueue<Tutoria>(10, this.comparadorTutorias);
		String cadena = "[";
		
		while (this.colaTutorias.size() != 0) {
			Tutoria peek = this.colaTutorias.remove();
			cadena += peek;
			if (this.colaTutorias.size() != 0)
				cadena += ", ";
			colaAux.add(peek);
		}
		
		cadena += "]";
		
		// para recuperar colaTutorias a partir de colaAux
		
		while (colaAux.size() != 0) {
			this.colaTutorias.add(colaAux.remove());
		}
		
		return cadena;
	}
```

- **comprobarColision(Tutoria tut)**: retorna un boolean true o false dependiendo de si la tutoría a añadir tut colisiona en fecha y en lugar con otra ya añadida en la cola anteriormente.

- **comprobarNumTutoria(Tutoria t):** retorna un boolean true o false dependiendo de si ya existe una tutoria con el número de tutoría de t en la cola.

- **comprobarDisponibilidad(Tutoria t)**: retorna un boolean true o false dependiendo de si la fecha de la tutoría está entre las fechas disponibles del alumno y el profesor

- **concertarTutoria (Tutoria t):** comprueba que no haya colisiones, ni números de tutoría iguales y que tanto el alumno como el profesor estén disponibles en la fecha de 
la tutoría y, si está todo correcto, añade la tutoría t a la cola de tutorías. Si hay colisión de fechas se lanza la **ColissionException**

- **cancelarTutoria(Tutoria t):** cancela, si se puede, la tutoría t de la cola de Tutorías


### 2.5) Paquete main
#### 2.5 - Paquete main: Clase Main

Clase principal del programa, donde realizaremos las reservas de tutorías y demostraremos la funcionalidad del programa.

Lo que hacemos es:

1) Creamos un ArrayList de alumnos y otro de profesores
2) Creamos la cola de tutorías colaTutorias
3) Creamos los alumnos y profesores y los guardamos en su ArrayList correspondiente
4) Creamos las fechas y tanto los alumnos como los profesores añaden a su lista de fechas disponibles las que consideren
5) Creamos las tutorías pertinentes y las añadimos: 
 - Tutoria 1 se añade correctamente
 - Tutoria 2 no se añade debido a que ya hay una tutoría en esa fecha y ese lugar: Salta tutorias.CollisionException
 - Tutorias 3 y 4 se añaden correctamente
 - Tutoría 5 no se añade debido a falta de disponibilidad
 - Tutorías 6 y 7 se añaden correctamente
 
6) Cancelamos la tutoría 7

















      






