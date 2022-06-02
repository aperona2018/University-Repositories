# Ampliación de Sistemas Telemáticos - Práctica 1: p1-introEclipse
## Autor: Antonio Perona Martínez @aperona2018
## Ingeniería Telemática

## Especificaciones del programa:

* Versión de Java Usada: JavaSE-13
* Indentación: 4 espacios por tabulación

## Contenidos del programa:

Este programa contiene 3 **paquetes**:

* **alumnos**
* **array**
* **main**

### Contenidos del programa: Paquete alumnos

Este paquete contiene 4 **clases**:

* **Alumno** (Clase padre)
* **Nuevo**
* **Repetidor**
* **Erasmus**

#### Contenidos del paquete alumnos: Clase Alumno

Clase padre de las demás, tiene 5 **atributos**

* nombre
* apellidos
* titulacion
* expediente
* precio

Además, tiene 3 **constantes**. **OJO:** Estas constantes solo las usaremos para el método **int precio(int tipoalumno)**, pues la práctica nos lo pide.


Asimismo, tiene los siguientes **métodos**:

* **getters y setters de los atributos y constantes**
* **int precio(int tipoalumno)**: Devuelve el precio de cada tipo de alumno dada una entrada entera (0 para Nuevo, 1 para Repetidor, 2 para Erasmus) o 0 si es otro. **OJO:** Este método NO lo usaremos regularmente, no obstante lo deberemos crear y probar al pedírnoslo la práctica. Sin embargo, llamar al getter getPrecio() para obtener el precio del alumno es una manera más elegante.

* **String toString()**: Retorna una cadena con el nombre y apellidos del alumno que llame a este método. 

* **String infoToString()**: Retorna una cadena con toda la información del alumno que llame a este método. 

* **void cambiarTitulacion(String titulacion)**: Cambia la titulación del alumno que llama al método por la titulación pasada por la entrada.


#### Contenidos del paquete alumnos: Clases Nuevo, Repetidor y Erasmus

Estas clases heredan los atributos de su clase padre, es decir, la clase Alumno. 

Asimismo, estas clases de diferencian únicamente por su atributo **precio**, el cual varía y se establece en su constructor:

* 60 euros para Nuevo
* 70 euros para Repetidor
* 80 euros para Erasmus


#### Contenidos del paquete array: Clase ArrayAlumnos

Creamos un array de objetos Alumno, el numero de posiciones lo pasamos en el constructor de la clase.

Asimismo, tiene los siguientes **métodos**:

* **int size()**: Devuelve la longitud del array

* **void add(Alumno a)**: Añade el alumno a por el último elemento libre del array. **NOTA**: Este método no comprueba si el elemento del array se ha llenado con otro método, simplemente "machacaría" dicho valor.

* **bolean ins(int posicion, Alumno a):** Añade un alumno a en una posicion determinada del array, si es posible, lo hace y retorna True, si no, retorna False

* **void cambiarTitulaciones(String titulacion):** Cambia todas las titulaciones de los alumnos del array por la escogida en la entrada. 
**OJO:** Aunque nos lo pidan en el paquete main, considero que es más óptimo, elegante y razonable tener este método en la clase ArrayAlumnos. 

* **String titulacionesToString():** retorna un string con todas las titulaciones de los alumnos del array. Lo usaremos para comprobar el funcionamiento de cambiarTitulaciones()

* **Alumno get(int i)**: retorna el alumno de la determinada posición dada en la entrada.

* **int getPrecioTotal()**: Retorna el precio total a pagar de todos los alumnos que contiene el array.
**OJO:** Aunque nos lo pidan en el paquete main, considero que es más óptimo, elegante y razonable tener este método en la clase ArrayAlumnos. 

* **String toString()**: Retorna el String correspondiente al array que lo llama. **NOTA:** Este método utiliza deepToString contenida en **java.util.Arrays**


#### Contenidos del paquete main: Clase Main

Clase "principal" del programa.

En esta clase, en su método main, podemos ver:

* Creamos 2 alumnos nuevos, 3 alumnos repetidores y 1 alumno erasmus.
* Probamos el método **int precio(int tipoalumno)**
* Probamos el método **String infoToString()**
* Probamos el método **void cambiarTitulacion(String titulacion)**


* Probamos el método **int size()**
* Probamos el método **void add(Alumno a)** con 5 alumnos.
* Probamos el método **String toString()** de la clase **ArrayAlumnos**
* Probamos el método **boolean ins(int posicion, Alumno a)**
* Probamos el método **Alumno get(int i)**
* Probamos el método **void cambiarTitulaciones(String titulacion)**
* Probamos el método **int getPrecioTotal()**





