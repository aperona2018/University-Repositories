# Ampliación de Sistemas Telemáticos: Práctica 3 -> P3-DocPruebas
## Antonio Perona Martínez @aperona2018
## Ingeniería Telemática

## Ejercicios:

### Ejercicio 1: Diagrama UML de la Práctica 2

Como se puede observar tanto en el diagrama UML como en su respectiva exportación en pdf, creamos las clases pertinentes: 

- **Alumno**: Que mantiene una relación de **herencia**, siendo esta la clase padre de:
   * Nuevo
   * Erasmus
   * Repetidor
   
- **Profesor**

Tanto **Alumno** como **Profesor** mantienen una relación de **composición** con la clase **Fecha** de 1..n (Es decir, al menos, cada alumno o profesor tendrán una Fecha disponible, con un máximo indefinido)

- **Tutoria**: Que mantiene una relación de **composición** con:
   * Alumno
   * Profesor
   * Fecha
   * InfoTutoria
   
Todas con 1..1 (Es decir, sólo habrá 1 **Alumno**, 1 **Profesor**, 1 **Fecha** y 1 **InfoTutoría** en cada **Tutoría**)

- **ColaTutorias**: Que tiene una relación de **uso** con una **PriorityQueue** y con la clase **ComparadorTutorias**, que a su vez esa mantiene otra relación de **uso** con un **Comparator**
   * A su vez, **ColaTutorias** mantiene una relación de **composición** con **Tutoria** de 0..n, puesto que no es estrictamente necesario que **ColaTutorias** tenga al menos 1 **Tutoria**
   

### Ejercicio 2: Documentación Javadoc de la Práctica 2

La documentación pertinente de la Práctica 2 en **Javadoc** se puede encontrar en la carpeta **doc**, donde, si se quiere ver la página correspondiente, se deberá acceder a **index.html**
En él describo brevemente las clases creadas y resumo los métodos más peculiares e importantes de mi programa.


### Ejercicio 3: Pruebas unitarias con JUnit y EclEmma

Mediante JUnit realizamos las pruebas pertinentes a casa clase:

- **AlumnoTest**: Tenemos los siguientes métodos de Test
   * **añadirFecha()**: Se comprueba que se pueda añadir una fecha a la lista de fechas disponibles del alumno
   * **eliminarFecha()**: Se comprueba que se pueda eliminar una fecha a la lista de fechas disponibles del alumno
   * **cambioTitulacion()**: Se comprueba que se pueda cambiar de titulación del alumno
   
- **ProfesorTest**: Tenemos los siguientes métodos de Test
   * **añadirFecha()**: Se comprueba que se pueda añadir una fecha a la lista de fechas disponibles del profesor
   * **eliminarFecha()**: Se comprueba que se pueda eliminar una fecha a la lista de fechas disponibles del profesor
   
- **ColaTutoriasTest**: Tenemos los siguientes métodos de Test
   * **concertarConDisponibilidad()**: Se comprueba que, dada la disponibilidad, se pueda concertar una tutoría y se añada en la cola de tutorías.
   * **concertarSinDisponibilidad()**: Se comprueba que, sin disponibilidad, no se pueda concertar una tutoría.
   * **cancelarTutoria()**: Se comprueba que se pueda cancelar una tutoría de la cola de Tutorías
   * **comprobandoColision()**: Se comprueba que no se pueda tener una tutoría si ya hay otra en esa fecha y lugar.


